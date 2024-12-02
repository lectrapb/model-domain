package com.app.domain.share.common.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AggregateRoot {

    private List<DomainEvent<?>> domainEvents = new ArrayList<>();

    public List<DomainEvent<?>> pullDomainEvents() {
        List<DomainEvent<?>> events = domainEvents;
        domainEvents = Collections.emptyList();
        return events;
    }

    public void record(DomainEvent<?> event) {
        domainEvents.add(event);
    }
}
