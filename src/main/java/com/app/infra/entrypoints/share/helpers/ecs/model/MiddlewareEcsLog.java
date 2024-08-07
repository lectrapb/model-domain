package com.app.infra.entrypoints.share.helpers.ecs.model;

public abstract class MiddlewareEcsLog {

    public MiddlewareEcsLog next;
    public abstract void handler(Throwable request,
                                 String service);
    public abstract MiddlewareEcsLog setNext(MiddlewareEcsLog next);
}
