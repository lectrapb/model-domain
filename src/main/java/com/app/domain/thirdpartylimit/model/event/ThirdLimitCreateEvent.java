package com.app.domain.thirdpartylimit.model.event;

import com.app.domain.share.common.model.DomainEvent;
import com.app.domain.thirdpartylimit.model.value.EventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class ThirdLimitCreateEvent extends DomainEvent<ThirdLimitCreateEvent.Data> {



    public ThirdLimitCreateEvent(String id,
                                 String channel,
                                 String customerId,
                                 String status) {
        super(EventType.COMMAND.getValue(),
                "api/createThirdLimit",
                "createThirdLimit",
                Data.builder()
                        .id(id)
                        .channel(channel)
                        .customerId(customerId)
                        .status(status).build());

    }

    @Builder
    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data{
        private String id;
        private String channel;
        private String customerId;
        private String status;
    }




}
