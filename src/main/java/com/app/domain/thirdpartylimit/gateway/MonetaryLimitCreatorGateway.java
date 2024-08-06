package com.app.domain.thirdpartylimit.gateway;

import com.app.domain.share.model.cqrs.ContextData;
import com.app.domain.customlimit.model.Command;
import com.app.domain.thirdpartylimit.model.ThirdPartyLimit;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface MonetaryLimitCreatorGateway {

    Mono<Void> save(Command<ThirdPartyLimit, ContextData> command);
}
