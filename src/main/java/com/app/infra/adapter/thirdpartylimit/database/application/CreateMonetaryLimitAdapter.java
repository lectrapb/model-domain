package com.app.infra.adapter.thirdpartylimit.database.application;

import com.app.domain.share.model.cqrs.ContextData;
import com.app.domain.customlimit.model.Command;
import com.app.domain.thirdpartylimit.gateway.MonetaryLimitCreatorGateway;
import com.app.domain.thirdpartylimit.model.ThirdPartyLimit;
import com.app.infra.adapter.thirdpartylimit.database.domain.ThirdPartyLimitData;
import com.app.infra.adapter.thirdpartylimit.database.infra.MonetaryLimitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@AllArgsConstructor
public class CreateMonetaryLimitAdapter implements MonetaryLimitCreatorGateway {

    private final MonetaryLimitRepository repository;

    public Mono<Void> save(ThirdPartyLimit thirdPartyLimit) {

        ThirdPartyLimitData data = MapperThirdPartyLimit.toData(thirdPartyLimit);
        return repository.save(data)
                .flatMap(dto -> Mono.empty());
    }

    @Override
    public Mono<Void> save(Command<ThirdPartyLimit, ContextData> command) {
        ThirdPartyLimitData data = MapperThirdPartyLimit.toData(command.payload());
        return repository.save(data)
                .flatMap(dto -> Mono.empty());
    }
}
