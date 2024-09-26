package com.app.application.thirdpartylimit;

import com.app.domain.share.model.cqrs.ContextData;
import com.app.domain.customlimit.model.Command;
import com.app.domain.customlimit.model.Query;
import com.app.domain.share.model.cqrs.MonetaryLimitCreate;
import com.app.domain.share.gateway.labels.UseCase;
import com.app.domain.share.value.StatusLimitAvailable;
import com.app.domain.thirdpartylimit.gateway.MonetaryLimitCreatorGateway;
import com.app.domain.thirdpartylimit.model.Channel;
import com.app.domain.thirdpartylimit.model.ThirdPartyLimit;
import com.app.domain.thirdpartylimit.value.Customer;
import com.app.domain.thirdpartylimit.value.StatusMonetaryLimit;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Map;


@UseCase
@AllArgsConstructor
public class MonetaryLimitsCreator {

    private final MonetaryLimitCreatorGateway repository;
/*
    public Mono<Void> addLimit(MonetaryLimitCreate limitCreate){


        return  Mono.fromCallable(() -> new ThirdPartyLimit(
                        Customer.of(limitCreate.getIdentification().getType(),
                                limitCreate.getIdentification().getNumber()),
                        new StatusMonetaryLimit(StatusLimitAvailable.ENABLE.name()),
                        new Channel(limitCreate.getChannel().getCode()))
                )

                .flatMap(repository::save);
    }
*/
    public Mono<Void> addLimit(Command<MonetaryLimitCreate, ContextData> command){

                var thirdParty = new ThirdPartyLimit(
                        Customer.of(command.payload().getIdentification().getType(),
                                command.payload().getIdentification().getNumber()),
                        new StatusMonetaryLimit(StatusLimitAvailable.ENABLE.name()),
                        new Channel( command.payload().getChannel().getCode()));

                var thirpartyLimit = Map.of("result", 1,
                        "id", 100);
                var querySuid = new Query<>(thirpartyLimit, command.context());
                var result = repository.searchSuid(querySuid);

                //----
                // put, create and Update son commandos
                return Mono.fromCallable(() ->
                                new Command<>(thirdParty, command.context()))
                       .flatMap(repository::save);

    }

    public Mono<Query<MonetaryLimitCreate, ContextData>> addLimit(Query<MonetaryLimitCreate, ContextData> query){

        return null;
    }
}
