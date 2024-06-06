package com.app.infra.entrypoints.share.ecs.model;

public abstract class MiddlewareEcsLog {

    public MiddlewareEcsLog next;
    public abstract void handler(Throwable request);
    public abstract MiddlewareEcsLog setNext(MiddlewareEcsLog next);
}
