package com.app.infra.adapter.share.bus.command.application;



import com.app.application.thirdpartylimit.ThirdLimitCreateCommand;
import com.app.application.thirdpartylimit.ThirdLimitCreateHandler;
import com.app.domain.share.bus.command.CommandData;
import com.app.domain.share.bus.command.CommandHandler;
import com.app.domain.share.bus.command.CommandNotRegisteredError;
import com.app.domain.share.common.gateway.labels.DomainService;

import java.util.HashMap;


@DomainService
public class CommandHandlersInformation {

    HashMap<Class<? extends CommandData>, Class<? extends CommandHandler>> indexedCommandHandlers;

    public CommandHandlersInformation() {
        indexedCommandHandlers = new HashMap<>();
        registerCommandHandlers();
    }

    private void registerCommandHandlers() {
        indexedCommandHandlers.put(ThirdLimitCreateCommand.class, ThirdLimitCreateHandler.class);
    }

    public Class<? extends CommandHandler> search(Class<? extends CommandData> commandClass) throws CommandNotRegisteredError {
        Class<? extends CommandHandler> commandHandlerClass = indexedCommandHandlers.get(commandClass);


        if (null == commandHandlerClass) {
            throw new CommandNotRegisteredError(commandClass);
        }

        return commandHandlerClass;
    }
}
