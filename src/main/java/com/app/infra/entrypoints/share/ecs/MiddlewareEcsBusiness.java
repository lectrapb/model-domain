package com.app.infra.entrypoints.share.ecs;




import com.app.domain.share.exception.BusinessException;
import com.app.infra.entrypoints.share.ecs.model.LogException;
import com.app.infra.entrypoints.share.ecs.model.LoggerEcs;
import com.app.infra.entrypoints.share.ecs.model.MiddlewareEcsLog;

import java.util.UUID;

public class MiddlewareEcsBusiness extends MiddlewareEcsLog {

    private MiddlewareEcsLog next;
    @Override
    public void handler(Throwable request) {
         if(request instanceof BusinessException exp){

             System.out.println(" is an object of BusinessException Exception: "
                     + exp.getMessage());

             var errorLog =new LogException.ErrorLog(
                     exp.getConstantBusinessException().getInternalMessage(),
                     exp.getConstantBusinessException().getInternalMessage(),
                     exp.getConstantBusinessException().getInternalMessage());
             //Load if it arrives
             var messageId = UUID.randomUUID().toString();
             var service = "api-auth";
             var logExp = LogException.builder()
                     .messageId(messageId)
                     .service(service)
                     .error(errorLog)
                     .level(LogException.Level.ERROR)
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
