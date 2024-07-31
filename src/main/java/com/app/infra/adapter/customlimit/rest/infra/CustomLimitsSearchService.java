package com.app.infra.adapter.customlimit.rest.infra;

import com.app.domain.share.exception.BusinessException;
import com.app.domain.share.exception.ConstantBusinessException;
import com.app.domain.share.exception.ecs.BusinessExceptionECS;
import com.app.infra.adapter.customlimit.rest.domain.ConstantHeader;
import com.app.infra.adapter.customlimit.rest.domain.SearchCustomLimit;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;


@Service
@Slf4j
public class CustomLimitsSearchService {

    private final WebClient client;
    private final String CAUSE = "cause";
    private final String SERVICE_KEY  = "service";
    private final String SERVICE_NAME = "getIdentity-suid";


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

        return httpGet(responseType, url, dataHeader);

    }

    private <T> Mono<T> httpGet(Class<T> responseType, String url, MultiValueMap<String, String> dataHeader) {
        return client
                .get()
                .uri(url)
                .headers(it -> it.addAll(dataHeader))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        clientResponse -> makeHttpRequest(clientResponse))
                .onStatus(HttpStatusCode::is5xxServerError,
                        clientResponse -> makeHttpRequest(clientResponse))
                .onStatus(
                        status -> status.value() == 204,
                        clientResponse -> Mono.empty())
                .bodyToMono(responseType)
                .onErrorResume(Throwable.class, e -> (e instanceof BusinessException)?Mono.error(e)
                        :Mono.error(new BusinessException(ConstantBusinessException.UNKNOWN_EXCEPTION,
                            Map.of(CAUSE, e.getCause().toString(), SERVICE_KEY,SERVICE_NAME))  ));
    }

    private Mono<BusinessException> makeHttpRequest(ClientResponse clientResponse) {
        return clientResponse.bodyToMono(String.class)
                .flatMap(body -> {
                    try {
                        var objectMapper = new ObjectMapper();
                        var bodyReplace =body.replace("\\n", "")
                                .replace("\\r", "")
                                .replace("\\t", "")
                                .replaceAll("\\s+", "")
                                .replace("\"\"", "\"");
                        var map = objectMapper.readValue(bodyReplace, Map.class);
                        return Mono.error(new BusinessException(ConstantBusinessException.WRONG_ANSWER__REQUEST_EXCEPTION_SUID,
                               map, Map.of(BusinessExceptionECS.MESSAGE_ID, UUID.randomUUID().toString())) );
                    } catch (Exception e) {
                        return Mono.error(new BusinessException(ConstantBusinessException.WRONG_ANSWER__REQUEST_EXCEPTION_SUID,
                                body));

                    }
                });
    }

}
