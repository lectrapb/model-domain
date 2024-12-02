package com.app.domain.share.logs.gateway;

import com.app.domain.share.cqrs.model.Command;
import com.app.domain.share.cqrs.model.ContextData;
import reactor.core.publisher.Mono;

import java.util.Map;

@FunctionalInterface
public interface LogsSendGateway {

    Mono<Void>  register(Command<Map<String, String>, ContextData> command);
}
