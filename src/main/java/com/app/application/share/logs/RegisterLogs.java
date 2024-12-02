package com.app.application.share.logs;

import com.app.domain.share.common.gateway.labels.UseCase;
import com.app.domain.share.cqrs.model.Command;
import com.app.domain.share.cqrs.model.ContextData;
import com.app.domain.share.logs.gateway.LogsSendGateway;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Map;


@UseCase
@AllArgsConstructor
public class RegisterLogs {

    private final LogsSendGateway logsSendGateway;

    public Mono<Void> persist(Command<Map<String, String>, ContextData> command){

          return logsSendGateway.register(command);
    }
}
