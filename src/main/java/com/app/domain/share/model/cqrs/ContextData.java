package com.app.domain.share.model.cqrs;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContextData {

    private String messageId;
}
