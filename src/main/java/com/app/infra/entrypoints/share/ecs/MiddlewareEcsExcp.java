package com.app.infra.entrypoints.share.ecs;


import com.app.infra.entrypoints.share.ecs.model.LogException;
import com.app.infra.entrypoints.share.ecs.model.LoggerEcs;
import com.app.infra.entrypoints.share.ecs.model.Mapper;
import com.app.infra.entrypoints.share.ecs.model.MiddlewareEcsLog;

public class MiddlewareEcsExcp extends MiddlewareEcsLog {

    private MiddlewareEcsLog next;
    @Override
    public void handler(Throwable request) {
         if(request  instanceof Exception exp){

             var errorLog = new LogException.ErrorLog(
                     exp.getClass().getName(),
                     exp.getMessage(),
                     Mapper.getStackTraceAsString(exp));
             var logExp = LogException.builder()
                     .service("")
                     .error(errorLog)
                     .level(LogException.Level.FATAL)
                     .build();
             LoggerEcs.print(logExp);
         }else if(next != null){
             next.handler(request);
         }
    }



    @Override
    public MiddlewareEcsLog setNext(MiddlewareEcsLog next) {
        this.next = next;
        return this;
    }
}
