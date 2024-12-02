package com.app.domain.share.common.model;

import com.app.domain.thirdpartylimit.model.value.UUID;
import lombok.Getter;

import java.time.Instant;


@Getter
public abstract class DomainEvent<T extends DomainEvent<T>> {

    private final String  type;
    private final String  source;
    private final String  id;
    private final String  subject;
    private final String  dataContentType;
    private final String  extensions;
    private final Instant time;



    public DomainEvent(String type, String source, String subject){
        this.type = type;
        this.source = source;
        this.subject = subject;
        this.id = new UUID().value();
        this.dataContentType = "application/json";
        this.extensions = "";
        this.time = Instant.now();
    }

    public abstract T getData();



}
