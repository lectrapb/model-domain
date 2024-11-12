package com.app.domain.share.bus.command;

import reactor.core.publisher.Mono;

public interface CommandHandler <T extends CommandData>{
    Mono<Void> handle(T command);
}
