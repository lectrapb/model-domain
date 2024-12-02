package com.app.domain.thirdpartylimit.model.value;

public enum EventType {
    COMMAND("command"),
    QUERY("query"),
    EVENT("event");

    private final String value;

    EventType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
