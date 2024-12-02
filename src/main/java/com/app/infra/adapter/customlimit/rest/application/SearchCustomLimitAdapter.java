package com.app.infra.adapter.customlimit.rest.application;

import com.app.domain.customlimit.gateway.SearchCustomLimitGateway;
import com.app.domain.share.cqrs.model.ContextData;
import com.app.domain.share.cqrs.model.Query;
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
    public Mono<Query<Object, ContextData>> customLimit(Query<Map<String, String>, ContextData> query) {
        String URL_TEST = "URL-TEST";
        var url = query.payload().get(URL_TEST);
       return searchService.buildException(Object.class,
                query.payload(), Object.class,
                url).map(o -> new Query<>(o, query.context()));

    }
}
