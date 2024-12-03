package com.app.domain.share.common.model;

import com.app.domain.thirdpartylimit.model.value.UUID;
import lombok.Getter;

import java.time.Instant;


@Getter
public abstract class DomainEvent<T> {

    private final String  type;
    private final String  source;
    private final String  id;
    private final String  subject;
    private final String  dataContentType;
    private final String  extensions;
    private final Instant time;
    private final T data;



    public DomainEvent(String type,
                       String source,
                       String subject, T data){
        this.type = type;
        this.source = source;
        this.subject = subject;
        this.id = new UUID().value();
        this.dataContentType = "application/json";
        this.extensions = "";
        this.time = Instant.now();
        this.data = data;
    }

    @Override
    public String toString() {
        return "DomainEvent{" +
                "type='" + type + '\'' +
                ", source='" + source + '\'' +
                ", id='" + id + '\'' +
                ", subject='" + subject + '\'' +
                ", dataContentType='" + dataContentType + '\'' +
                ", extensions='" + extensions + '\'' +
                ", time=" + time +
                ", data=" + data +
                '}';
    }
}
