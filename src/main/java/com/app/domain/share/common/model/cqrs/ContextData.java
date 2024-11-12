package com.app.domain.share.common.model.cqrs;

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
