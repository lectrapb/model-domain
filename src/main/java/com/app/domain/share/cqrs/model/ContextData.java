package com.app.domain.share.cqrs.model;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContextData {

    private String messageId;
    private String channel;
    private String xRequestId;

}
