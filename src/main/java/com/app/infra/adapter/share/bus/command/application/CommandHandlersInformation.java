package com.app.infra.adapter.share.bus.command.application;



import com.app.domain.share.bus.command.gateway.CommandBusData;
import com.app.domain.share.bus.command.gateway.CommandHandler;
import com.app.domain.share.bus.command.model.CommandNotRegisteredError;
import com.app.domain.share.bus.command.gateway.label.CommandHandlerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.HashMap;


@Configuration
public class CommandHandlersInformation {

    HashMap<Class<? extends CommandBusData>, Class<? extends CommandHandler>> indexedCommandHandlers;
    private final ApplicationContext applicationContext;

    public CommandHandlersInformation(ApplicationContext context) {
        applicationContext = context;
        indexedCommandHandlers = new HashMap<>();
        registerCommandHandlers();
    }

    private void registerCommandHandlers() {
        Arrays.stream(applicationContext.getBeanDefinitionNames())
                .map(applicationContext::getType)
                        .filter(beanType -> beanType != null && beanType.isAnnotationPresent(CommandHandlerService.class))
                                .forEach(beanType ->{
                                    var genericType = (ParameterizedType) beanType.getGenericInterfaces()[0];
                                    var commandType = (Class<? extends CommandBusData>) genericType.getActualTypeArguments()[0];
                                    var handlerCommandType =(Class<? extends CommandHandler>) beanType;
                                    indexedCommandHandlers.put(commandType, handlerCommandType );
                                });
    }

    public Class<? extends CommandHandler> search(Class<? extends CommandBusData> commandClass) throws CommandNotRegisteredError {
        Class<? extends CommandHandler> commandHandlerClass = indexedCommandHandlers.get(commandClass);
        if (null == commandHandlerClass) {
            throw new CommandNotRegisteredError(commandClass);
        }
        return commandHandlerClass;
    }
}
