package com.app.domain.thirdpartylimit.model.event;

import com.app.domain.share.common.model.DomainEvent;
import com.app.domain.thirdpartylimit.model.value.EventType;


public class ThirdLimitCreateEvent extends DomainEvent<ThirdLimitCreateEvent> {

    private String id;
    private String channel;
    private String customerId;
    private String status;

    public ThirdLimitCreateEvent(String id,
                                 String channel,
                                 String customerId,
                                 String status) {
        super(EventType.COMMAND.getValue(),
                "api/createThirdLimit",
                "createThirdLimit");
        this.id = id;
        this.channel = channel;
        this.customerId = customerId;
        this.status = status;
    }

    @Override
    public ThirdLimitCreateEvent getData() {

        return this;
    }

    @Override
    public String toString() {
        return "ThirdLimitCreateEvent{" +
                "id='" + id + '\'' +
                ", channel='" + channel + '\'' +
                ", customerId='" + customerId + '\'' +
                ", status='" + status + '\'' +
                ", type='" + super.getType() + '\'' +
                ", source='" + super.getSource() + '\'' +
                ", idEvent='" + super.getId() + '\'' +
                ", subject='" + super.getSubject() + '\'' +
                ", dataContentType='" + super.getDataContentType() + '\'' +
                ", extensions='" + super.getExtensions() + '\'' +
                ", time='" + super.getTime() + '\'' +
                '}';
    }
}
