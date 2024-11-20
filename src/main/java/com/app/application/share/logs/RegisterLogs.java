package com.app.application.share.logs;

import com.app.domain.share.common.gateway.labels.UseCase;
import com.app.domain.share.common.model.cqrs.Command;
import com.app.domain.share.common.model.cqrs.ContextData;
import reactor.core.publisher.Mono;

import java.util.Map;


@UseCase
public class RegisterLogs {



    public Mono<Void> persist(Command<Map<String, String>, ContextData> command){

          return Mono.empty();
    }
}
