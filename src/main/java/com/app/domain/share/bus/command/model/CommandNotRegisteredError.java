package com.app.domain.share.bus.command.model;

import com.app.domain.share.bus.command.gateway.CommandBusData;

public final  class CommandNotRegisteredError extends RuntimeException {

    public CommandNotRegisteredError(Class<? extends CommandBusData> command) {
        super(String.format("The command <%s> hasn't a command handler associated", command.toString()));
    }
}
