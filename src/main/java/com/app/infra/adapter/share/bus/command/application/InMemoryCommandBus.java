package com.app.infra.adapter.share.bus.command.application;

import com.app.domain.share.bus.command.gateway.CommandBus;
import com.app.domain.share.bus.command.gateway.CommandBusData;
import com.app.domain.share.bus.command.gateway.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;


@Configuration
public class InMemoryCommandBus  implements CommandBus {

    private final CommandHandlersInformation information;
    private final ApplicationContext context;

    @Autowired
    public InMemoryCommandBus(CommandHandlersInformation information, ApplicationContext context) {
        this.information = information;
        this.context = context;
    }

    //------------------------------------------------------
    //
    @Override
    public Mono<Void> dispatch(CommandBusData command) {
        Class<? extends CommandHandler> commandHandlerClass = information.search(command.getClass());
        CommandHandler handler = context.getBean(commandHandlerClass);
        return handler.handle(command);
    }
}



