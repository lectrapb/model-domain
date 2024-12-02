package com.app.application.customlimit;


import com.app.application.share.logs.RegisterLogs;
import com.app.application.share.logs.RegisterLogsCommand;
import com.app.domain.share.bus.command.gateway.CommandBus;
import com.app.domain.share.common.gateway.labels.UseCase;
import com.app.domain.share.cqrs.model.Command;
import com.app.domain.share.cqrs.model.ContextData;
import com.app.domain.share.cqrs.model.Query;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
import  com.app.domain.customlimit.gateway.SearchCustomLimitGateway;

import java.util.Map;

@UseCase
@AllArgsConstructor
public class MonetaryClientSearch {


    private final SearchCustomLimitGateway search;
    private final RegisterLogs registerLogs;
    private final CommandBus commandBus;

    public Mono<Query<Object, ContextData>> searchLimit(Query<Map<String, String>, ContextData> query){
         var successLog = new Command<>(Map.of("LOG-500", "SUCCESS"), query.context());
         return search.customLimit(query)
                 .flatMap(data -> registerLogs.persist(successLog)
                              .then(Mono.just(data)));
    }

    public Mono<Query<Object, ContextData>> searchLimit2(Query<Map<String, String>, ContextData> query){
        var successLog = new Command<>(Map.of("LOG-500", "SUCCESS"), query.context());
        return search.customLimit(query)
                .flatMap(data ->  commandBus.dispatch(new RegisterLogsCommand(successLog))
                        .then(Mono.just(data)));
    }
}
