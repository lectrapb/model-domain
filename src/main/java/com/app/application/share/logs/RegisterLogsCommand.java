package com.app.application.share.logs;

import com.app.domain.share.bus.command.CommandBusData;
import com.app.domain.share.common.model.cqrs.Command;
import com.app.domain.share.common.model.cqrs.ContextData;

import java.util.Map;


public record RegisterLogsCommand(Command<Map<String, String>, ContextData> command) implements CommandBusData {

}
