package com.app.domain.share.bus.command;

import reactor.core.publisher.Mono;

public interface CommandBus {

    Mono<Void> dispatch(CommandData command) ;
}
