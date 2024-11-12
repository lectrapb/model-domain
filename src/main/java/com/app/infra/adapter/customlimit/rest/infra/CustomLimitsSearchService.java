package com.app.infra.adapter.customlimit.rest.infra;

import com.app.domain.share.common.model.exception.BusinessException;
import com.app.domain.share.common.model.exception.ConstantBusinessException;
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


@Service
@Slf4j
public class CustomLimitsSearchService {

    private final WebClient client;
    private final String CAUSE = "cause";
    private final String SERVICE_KEY = "service";
    private final String SERVICE_NAME = "getIdentity-suid";


    public CustomLimitsSearchService(WebClient client) {
        this.client = client;
    }

    public Mono<SearchCustomLimit> findCustomLimits(Map<String, String> meta, String url) {

        return buildException(String.class, meta, SearchCustomLimit.class, url);
    }


    public <T, R> Mono<T> buildException(R request,
                                         Map<String, String> meta,
                                         Class<T> responseType,
                                         String url) {
        var messageId = meta.get(ConstantHeader.MESSAGE_ID);
        var canal = meta.getOrDefault(ConstantHeader.HEADER_CONSUMER_ACRONYM, "channel");

        MultiValueMap<String, String> dataHeader = new LinkedMultiValueMap<>();
        dataHeader.add(ConstantHeader.HEADER_CONSUMER_ACRONYM, canal);
        dataHeader.add(ConstantHeader.HEADER_MESSAGE_ID_SUID, messageId);

        return httpGet(responseType, url, dataHeader, messageId);

    }

    private <T> Mono<T> httpGet(Class<T> responseType, String url,
                                MultiValueMap<String, String> dataHeader,
                                String messageId) {
        return client
                .get()
                .uri(url)
                .headers(it -> it.addAll(dataHeader))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        clientResponse -> buildException(clientResponse, messageId))
                .onStatus(HttpStatusCode::is5xxServerError,
                        clientResponse -> buildException(clientResponse, messageId))
                .onStatus(
                        status -> status.value() == 204,
                        clientResponse -> Mono.empty())
                .bodyToMono(responseType)
                .onErrorResume(Throwable.class, e -> (e instanceof BusinessException) ? Mono.error(e)
                        : Mono.error(new BusinessException(ConstantBusinessException.UNKNOWN_EXCEPTION,
                        Map.of(CAUSE, e.getCause().toString(), SERVICE_KEY, SERVICE_NAME))));
    }

    private Mono<BusinessException> buildException(ClientResponse clientResponse, String messageId) {
        return clientResponse.bodyToMono(String.class)
                .flatMap(body -> {
                    try {
                        var objectMapper = new ObjectMapper();
                        var bodyReplace = body.replaceAll("[\\n\\r\\t]+", "");
                        var map = objectMapper.readValue(bodyReplace, Map.class);
                        return Mono.error(new BusinessException(ConstantBusinessException.WRONG_ANSWER__REQUEST_EXCEPTION_SUID,
                                map, messageId));
                    } catch (Exception e) {
                        return Mono.error(new BusinessException(ConstantBusinessException.WRONG_ANSWER__REQUEST_EXCEPTION_SUID,
                                body));
                    }
                });
    }

}
