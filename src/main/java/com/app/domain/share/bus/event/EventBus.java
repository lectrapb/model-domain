package com.app.domain.share.bus.event;

import com.app.domain.share.common.model.DomainEvent;
import reactor.core.publisher.Mono;

import java.util.List;

public interface EventBus {

    Mono<Void> publish(final List<DomainEvent<?>> events );
}
