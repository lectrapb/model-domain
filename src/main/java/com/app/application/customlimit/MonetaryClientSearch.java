package com.app.application.customlimit;


import com.app.application.share.logs.RegisterLogs;
import com.app.domain.share.common.gateway.labels.UseCase;
import com.app.domain.share.common.model.cqrs.Command;
import com.app.domain.share.common.model.cqrs.ContextData;
import com.app.domain.share.common.model.cqrs.Query;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
import  com.app.domain.customlimit.gateway.SearchCustomLimitGateway;

import java.util.Map;

@UseCase
@AllArgsConstructor
public class MonetaryClientSearch {


    private final SearchCustomLimitGateway search;
    private final RegisterLogs registerLogs;

    public Mono<Query<Object, ContextData>> searchLimit(Query<Map<String, String>, ContextData> query){
         var successLog = new Command<>(Map.of("LOG-500", "SUCCESS"), query.context());
         return search.customLimit(query)
                 .flatMap(data -> registerLogs.persist(successLog)
                              .then(Mono.just(data)));
    }
}
