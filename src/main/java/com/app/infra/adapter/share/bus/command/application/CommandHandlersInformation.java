package com.app.infra.adapter.share.bus.command.application;



import com.app.application.share.logs.RegisterLogsCmdHandler;
import com.app.application.share.logs.RegisterLogsCommand;
import com.app.application.thirdpartylimit.ThirdLimitCreateCommand;
import com.app.application.thirdpartylimit.ThirdLimitCreateCmdHandler;
import com.app.domain.share.bus.command.CommandBusData;
import com.app.domain.share.bus.command.CommandHandler;
import com.app.domain.share.bus.command.CommandNotRegisteredError;
import com.app.domain.share.common.gateway.labels.DomainService;

import java.util.HashMap;


@DomainService
public class CommandHandlersInformation {

    HashMap<Class<? extends CommandBusData>, Class<? extends CommandHandler>> indexedCommandHandlers;

    public CommandHandlersInformation() {
        indexedCommandHandlers = new HashMap<>();
        registerCommandHandlers();
    }

    private void registerCommandHandlers() {
        indexedCommandHandlers.put(ThirdLimitCreateCommand.class, ThirdLimitCreateCmdHandler.class);
        indexedCommandHandlers.put(RegisterLogsCommand.class, RegisterLogsCmdHandler.class);
    }

    public Class<? extends CommandHandler> search(Class<? extends CommandBusData> commandClass) throws CommandNotRegisteredError {
        Class<? extends CommandHandler> commandHandlerClass = indexedCommandHandlers.get(commandClass);
        if (null == commandHandlerClass) {
            throw new CommandNotRegisteredError(commandClass);
        }
        return commandHandlerClass;
    }
}
