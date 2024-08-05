package com.app.infra.adapter.customlimit.rest.application;

import com.app.domain.customlimit.gateway.SearchCustomLimitGateway;
import com.app.infra.adapter.customlimit.rest.infra.CustomLimitsSearchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@AllArgsConstructor
public class SearchCustomLimitAdapter implements SearchCustomLimitGateway {

    private final CustomLimitsSearchService searchService;

    @Override
    public Mono<Object> customLimit(Map<String, String> meta) {
        String URL_TEST = "URL-TEST";
        var url = meta.get(URL_TEST);
        return searchService.buildException(Object.class,
                                             meta, Object.class,
                                             url);
    }
}
