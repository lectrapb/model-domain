package com.app.domain.share.bus.command.gateway;

import reactor.core.publisher.Mono;

public interface CommandBus {

    Mono<Void> dispatch(CommandBusData command) ;
}
