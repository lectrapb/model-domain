package com.app.domain.customlimit.gateway;

import reactor.core.publisher.Mono;

import java.util.Map;

public interface SearchCustomLimitGateway {
    Mono<Object> customLimit(Map<String, String> meta);
}
