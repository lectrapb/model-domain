package com.app.domain.customlimit.model;

public class Command <T,C>{

    private T payload;
    private C context;

    public Command(T payload, C context) {
        this.payload = payload;
        this.context = context;
    }

    public static <T, C> Command of(T payload, C context){

         return new Command<>(payload, context);
    }

    public T payload() {
        return payload;
    }

    public C context() {
        return context;
    }
}
