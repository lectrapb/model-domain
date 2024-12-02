package com.app.application.share.logs;

import com.app.domain.share.bus.command.gateway.CommandBusData;
import com.app.domain.share.cqrs.model.Command;
import com.app.domain.share.cqrs.model.ContextData;

import java.util.Map;


public record RegisterLogsCommand(Command<Map<String, String>, ContextData> command) implements CommandBusData {

}
