package com.app.domain.thirdpartylimit.gateway;

import com.app.domain.share.cqrs.model.Query;
import com.app.domain.share.cqrs.model.ContextData;
import com.app.domain.share.cqrs.model.Command;
import com.app.domain.thirdpartylimit.model.ThirdPartyLimit;
import reactor.core.publisher.Mono;

import java.util.Map;


public interface MonetaryLimitCreatorGateway {

    Mono<Void> save(Command<ThirdPartyLimit, ContextData> command);

    Mono<Query<Map, ContextData>> searchSuid(Query<Map<String, Integer>, ContextData> query);

}
