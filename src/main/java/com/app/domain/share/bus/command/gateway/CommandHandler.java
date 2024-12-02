package com.app.domain.share.bus.command.gateway;

import reactor.core.publisher.Mono;

public interface CommandHandler <T extends CommandBusData>{
    Mono<Void> handle(T commandData);
}
