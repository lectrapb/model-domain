package com.app.application.share.logs;

import com.app.domain.share.bus.command.CommandHandler;
import com.app.domain.share.common.gateway.labels.DomainService;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@DomainService
@AllArgsConstructor
public class RegisterLogsCmdHandler implements CommandHandler<RegisterLogsCommand> {

    private final RegisterLogs registerLogs;


    @Override
    public Mono<Void> handle(RegisterLogsCommand commandData) {
        return registerLogs.persist(commandData.command());
    }
}
