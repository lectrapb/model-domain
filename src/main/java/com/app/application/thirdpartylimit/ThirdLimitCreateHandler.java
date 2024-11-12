package com.app.application.thirdpartylimit;

import com.app.domain.customlimit.model.MonetaryLimitCreate;
import com.app.domain.share.common.gateway.labels.DomainService;
import com.app.domain.share.common.model.cqrs.Command;
import com.app.domain.share.bus.command.CommandHandler;
import com.app.domain.share.common.model.cqrs.ContextData;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@DomainService
@AllArgsConstructor
public class ThirdLimitCreateHandler implements CommandHandler<ThirdLimitCreateCommand> {

    private final MonetaryLimitsCreator monetaryLimitsCreator;

    @Override
    public Mono<Void> handle(ThirdLimitCreateCommand command) {

        var request = new Command<MonetaryLimitCreate, ContextData>(command.limitCreate(), command.contextData());
        return monetaryLimitsCreator.addLimit(request);
    }
}
