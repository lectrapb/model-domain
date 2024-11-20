package com.app.domain.share.common.model.cqrs;




public class Query<T,C>{

    private T payload;
    private C context;

    public Query(T payload, C context) {
        this.payload = payload;
        this.context = context;
    }

    public static <T, C> Query of(T payload, C context){

        return new Query<>(payload, context);
    }

    public T payload() {
        return payload;
    }

    public C context() {
        return context;
    }
}
