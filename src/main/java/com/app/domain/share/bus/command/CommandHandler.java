package com.app.domain.share.bus.command;

import reactor.core.publisher.Mono;

public interface CommandHandler <T extends CommandBusData>{
    Mono<Void> handle(T commandData);
}
