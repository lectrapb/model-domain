package com.app.domain.share.bus.command;

public final  class CommandNotRegisteredError extends RuntimeException {

    public CommandNotRegisteredError(Class<? extends CommandData> command) {
        super(String.format("The command <%s> hasn't a command handler associated", command.toString()));
    }
}
