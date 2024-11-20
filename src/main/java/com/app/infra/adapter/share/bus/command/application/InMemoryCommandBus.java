package com.app.infra.adapter.share.bus.command.application;

import com.app.domain.share.bus.command.CommandBus;
import com.app.domain.share.bus.command.CommandData;
import com.app.domain.share.bus.command.CommandHandler;
import com.app.domain.share.common.gateway.labels.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import reactor.core.publisher.Mono;


@DomainService
public class InMemoryCommandBus  implements CommandBus {

    private final CommandHandlersInformation information;
    private final ApplicationContext context;

    @Autowired
    public InMemoryCommandBus(CommandHandlersInformation information, ApplicationContext context) {
        this.information = information;
        this.context = context;
    }

    @Override
    public Mono<Void> dispatch(CommandData command) {
        Class<? extends CommandHandler> commandHandlerClass = information.search(command.getClass());
        CommandHandler handler = context.getBean(commandHandlerClass);
        return handler.handle(command);
    }
}



