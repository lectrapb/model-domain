package com.app.domain.customlimit.gateway;

import com.app.domain.share.cqrs.model.ContextData;
import com.app.domain.share.cqrs.model.Query;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface SearchCustomLimitGateway {
    Mono<Query<Object, ContextData>> customLimit(Query<Map<String, String>, ContextData> query);
}
