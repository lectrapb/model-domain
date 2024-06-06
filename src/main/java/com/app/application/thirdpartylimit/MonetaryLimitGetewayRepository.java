package com.app.application.thirdpartylimit;

import reactor.core.publisher.Mono;

public interface MonetaryLimitGetewayRepository {

    Mono<Void> save();
    Mono<Void> findAll();
    Mono<Void> findQuery();
}
