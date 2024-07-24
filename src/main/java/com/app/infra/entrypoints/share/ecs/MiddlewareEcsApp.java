package com.app.infra.entrypoints.share.ecs;


import com.app.domain.share.exception.AppException;
import com.app.infra.entrypoints.share.ecs.model.LogException;
import com.app.infra.entrypoints.share.ecs.model.LoggerEcs;
import com.app.infra.entrypoints.share.ecs.model.MiddlewareEcsLog;

public class MiddlewareEcsApp extends MiddlewareEcsLog {

    private MiddlewareEcsLog next;
    @Override
    public void handler(Throwable request, String service) {
         if( request instanceof AppException exp){
             System.out.println(" is an object of App Exception: "
                     + exp.getCode());

             var errorLog = LogException.ErrorLog.builder()
                     .type(exp.getCode())
                     .description(exp.getMessage())
                     .message(exp.getMessage())
                     .build();
             var logExp = LogException.builder()
                     .service(service)
                     .error(errorLog)
                     .level(LogException.Level.ERROR)
                     .build();
             LoggerEcs.print(logExp);
         }else if(next != null){
             next.handler(request, service);
         }

    }

    @Override
    public MiddlewareEcsLog setNext(MiddlewareEcsLog next) {
        this.next = next;
        return this;
    }
}
