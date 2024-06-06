package com.app.domain.thirdpartylimit.gateway;

import com.app.domain.thirdpartylimit.model.ThirdPartyLimit;
import reactor.core.publisher.Mono;

public interface MonetaryLimitCreatorGateway {
    Mono<Void> save(ThirdPartyLimit  thirdPartyLimit);
}
