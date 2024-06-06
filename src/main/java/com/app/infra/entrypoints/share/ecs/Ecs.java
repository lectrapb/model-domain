package com.app.infra.entrypoints.share.ecs;


import com.app.infra.entrypoints.share.ecs.model.MiddlewareEcsLog;

public final class Ecs {

    public static MiddlewareEcsLog build() {

        var ecsBusiness = new MiddlewareEcsBusiness();
        var ecsApp = new MiddlewareEcsApp();
        var ecsExp = new MiddlewareEcsExcp();
        var ecsTrow = new MiddlewareEcsTrow();
        return ecsBusiness.setNext(ecsApp.setNext(ecsExp.setNext(ecsTrow)));

    }

    public static Throwable build(Throwable throwable) {
        build().handler(throwable);
        return throwable;
    }


    private Ecs() {
    }
}
