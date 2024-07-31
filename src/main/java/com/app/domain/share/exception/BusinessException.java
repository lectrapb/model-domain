package com.app.domain.share.exception;

import com.app.domain.share.exception.ecs.BusinessExceptionECS;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

@Log4j2
public class BusinessException extends BusinessExceptionECS{



    public BusinessException(String message){
        super(message);
    }

    public BusinessException(ConstantBusinessException message) {
        super(message);
    }
    public BusinessException(ConstantBusinessException message, String optionalInfo) {
        super(message, optionalInfo);
    }

    public BusinessException(ConstantBusinessException message, Map<String, String> optionalInfoJson) {
        super(message, optionalInfoJson);
    }

    public BusinessException(ConstantBusinessException message, Map<String, String> optionalInfo, Map<String, String> metaInfo) {
        super(message, optionalInfo, metaInfo);
    }

    public static BusinessException loggingJsonOf(ConstantBusinessException constantBusinessException,
                                                  Map map, String format) throws JsonProcessingException {
        String ERROR_KEY = "output-request";
        var objectMapper = new ObjectMapper();
        var bodyStrLog = objectMapper.writeValueAsString(map);
        var bodyStr = bodyStrLog.replace("\"", "'");
        var errorMap = Map.of(ERROR_KEY,bodyStr);
        log.error(format, bodyStrLog);
        return new BusinessException(constantBusinessException,errorMap);

    }

    public static BusinessException loggingStringOf (ConstantBusinessException message, String bodyStrLog,
                                                     String format ) {
        String ERROR_KEY = "output-request";
        var errorMap =  Map.of(ERROR_KEY,bodyStrLog);
        log.error(format, bodyStrLog);
        return new BusinessException(message, errorMap);
    }
}
