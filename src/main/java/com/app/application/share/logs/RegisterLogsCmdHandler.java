package com.app.application.share.logs;

import com.app.domain.share.bus.command.gateway.CommandHandler;
import com.app.domain.share.bus.command.gateway.label.CommandHandlerService;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@CommandHandlerService
@AllArgsConstructor
public class RegisterLogsCmdHandler implements CommandHandler<RegisterLogsCommand> {

    private final RegisterLogs registerLogs;


    @Override
    public Mono<Void> handle(RegisterLogsCommand commandData) {
        return registerLogs.persist(commandData.command());
    }
}
