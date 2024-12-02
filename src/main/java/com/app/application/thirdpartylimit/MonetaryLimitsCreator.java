package com.app.application.thirdpartylimit;

import com.app.application.share.logs.RegisterLogsCommand;
import com.app.domain.customlimit.model.MonetaryLimitCreate;
import com.app.domain.share.bus.command.gateway.CommandBus;
import com.app.domain.share.common.gateway.labels.UseCase;
import com.app.domain.share.cqrs.model.Command;
import com.app.domain.share.cqrs.model.ContextData;
import com.app.domain.share.common.value.StatusLimitAvailable;
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
    private final CommandBus commandBus;

    public Mono<Void> addLimit(Command<MonetaryLimitCreate, ContextData> command){

                var successLog = new Command<>(Map.of("LOG-01", "SUCCESS"), command.context());
                var thirdParty = new ThirdPartyLimit(
                        Customer.of(command.payload().getIdentification().getType(),
                                command.payload().getIdentification().getNumber()),
                        new StatusMonetaryLimit(StatusLimitAvailable.ENABLE.name()),
                        new Channel( command.payload().getChannel().getCode()));

                return Mono.fromCallable(() ->
                                new Command<>(thirdParty, command.context()))
                        .flatMap(repository::save)
                        .then(commandBus.dispatch(new RegisterLogsCommand(successLog)));

    }



}
