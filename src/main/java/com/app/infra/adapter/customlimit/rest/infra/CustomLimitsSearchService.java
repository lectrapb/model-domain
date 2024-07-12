package com.app.infra.adapter.customlimit.rest.infra;

import com.app.domain.share.exception.BusinessException;
import com.app.domain.share.exception.ConstantBusinessException;
import com.app.infra.adapter.customlimit.rest.domain.ConstantHeader;
import com.app.infra.adapter.customlimit.rest.domain.SearchCustomLimit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.timeout.ReadTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.UnsupportedMediaTypeException;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;


@Service
@Slf4j
public class CustomLimitsSearchService {

    private final WebClient client;

 ;

    public CustomLimitsSearchService(WebClient client) {
        this.client = client;
    }

    public Mono<SearchCustomLimit> findCustomLimits(Map<String, String> meta, String url) {

        return makeHttpRequest(String.class,meta,SearchCustomLimit.class, url);
    }


    public <T, R> Mono<T> makeHttpRequest(R request,
                                          Map<String, String> meta,
                                          Class<T> responseType,
                                          String url ) {
        var messageId = meta.getOrDefault(ConstantHeader.HEADER_MESSAGE_ID_SUID, "uid");
        var canal = meta.getOrDefault(ConstantHeader.HEADER_CONSUMER_ACRONYM, "channel");

        MultiValueMap<String, String> dataHeader = new LinkedMultiValueMap<>();
        dataHeader.add(ConstantHeader.HEADER_CONSUMER_ACRONYM, canal);
        dataHeader.add(ConstantHeader.HEADER_MESSAGE_ID_SUID, messageId);

        return client
                .get()
                .uri(url)
                //.contentType(MediaType.APPLICATION_JSON)
                .headers(it -> it.addAll(dataHeader))
                //.body(Mono.just(request), request.getClass())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        clientResponse -> makeHttpRequest(clientResponse,
                                "Error call SUID Identity 5XX content:{}"))
                .onStatus(HttpStatusCode::is5xxServerError,
                        clientResponse ->  makeHttpRequest(clientResponse,
                                "Error call SUID Identity 5XX content:{}"))
                .onStatus(
                        status -> status.value() == 204,
                        clientResponse -> Mono.empty())
                .bodyToMono(responseType)
                .onErrorResume(Throwable.class,e ->
                {
                    System.out.println(e.getMessage());
                    return  Mono.error(new BusinessException(ConstantBusinessException.TIMEOUT_EXCEPTION, getBodyStr(e.getMessage())));
                });

    }

    private Mono<BusinessException> makeHttpRequest(ClientResponse clientResponse, String format) {
        return clientResponse.bodyToMono(String.class)
                .flatMap(body -> {
                    try {
                        var objectMapper = new ObjectMapper();
                        Map map = objectMapper.readValue(body, Map.class);
                        var bodyStr = objectMapper.writeValueAsString(map);
                        log.error(format, bodyStr);
                        return Mono.error(new BusinessException(ConstantBusinessException.WRONG_ANSWER__REQUEST_EXCEPTION,
                                body));
                    } catch (Exception e) {
                        log.error(format, body);
                        return Mono.error(new BusinessException(ConstantBusinessException.WRONG_ANSWER__REQUEST_EXCEPTION,
                                getBodyStr(body)));
                    }
                });
    }


    private String getBodyStr(String body){
        var objectMapper = new ObjectMapper();
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("error",body);
        String bodyStr = null;
        try {
            return bodyStr = objectMapper.writeValueAsString(errorMap);
        } catch (JsonProcessingException ex) {
            return bodyStr;
        }
    }




}
