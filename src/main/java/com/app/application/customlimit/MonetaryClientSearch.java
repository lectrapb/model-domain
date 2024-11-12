package com.app.application.customlimit;


import com.app.domain.share.common.gateway.labels.UseCase;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
import  com.app.domain.customlimit.gateway.SearchCustomLimitGateway;

import java.util.Map;

@UseCase
@AllArgsConstructor
public class MonetaryClientSearch {


    private final SearchCustomLimitGateway search;

    public Mono<Object> searchLimit(Map<String, String> meta){

         return search.customLimit(meta);
    }
}
