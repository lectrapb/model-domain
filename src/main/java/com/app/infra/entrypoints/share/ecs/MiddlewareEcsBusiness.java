package com.app.infra.entrypoints.share.ecs;

import com.app.domain.share.exception.ecs.BusinessExceptionECS;
import com.app.infra.entrypoints.share.ecs.model.LogException;
import com.app.infra.entrypoints.share.ecs.model.LoggerEcs;
import com.app.infra.entrypoints.share.ecs.model.MiddlewareEcsLog;

import java.util.UUID;

public class MiddlewareEcsBusiness extends MiddlewareEcsLog {


    private MiddlewareEcsLog next;

    @Override
    public void handler(Throwable request,
                        String service) {
         if(request instanceof BusinessExceptionECS exp){

             var errorLog = LogException.ErrorLog.builder()
                     .type(exp.getConstantBusinessException().getLogCode())
                     .description(exp.getConstantBusinessException().getInternalMessage())
                     .message(exp.getConstantBusinessException().getMessage())
                     .optionalInfo(exp.getOptionalInfo())
                     .build();

             var messageId =  exp.getMetaInfo()
                     .getOrDefault(BusinessExceptionECS.MESSAGE_ID,
                     UUID.randomUUID().toString());
             var logExp = LogException.builder()
                     .messageId(messageId)
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
