package com.app.domain.customlimit.gateway;

import com.app.domain.share.common.model.cqrs.ContextData;
import com.app.domain.share.common.model.cqrs.Query;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface SearchCustomLimitGateway {
    Mono<Query<Object, ContextData>> customLimit(Query<Map<String, String>, ContextData> query);
}
