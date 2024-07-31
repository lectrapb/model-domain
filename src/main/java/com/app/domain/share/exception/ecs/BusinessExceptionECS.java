package com.app.domain.share.exception.ecs;

import com.app.domain.share.exception.ConstantBusinessException;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Log4j2
public class BusinessExceptionECS extends RuntimeException {

    private  Map<String, String> optionalInfo = new HashMap<>();
    private  Map<String, String> metaInfo = new HashMap<>();
    private  ConstantBusinessException constantBusinessException = ConstantBusinessException.DEFAULT_EXCEPTION;
    private static final String EMPTY = "";
    private static final String OPTIONAL = "OPTIONAL";
    public  static final String MESSAGE_ID = "message-id";


    public BusinessExceptionECS(String message){
        super(message);
    }

    public BusinessExceptionECS(ConstantBusinessException message) {
        this(message, EMPTY);
    }

    public BusinessExceptionECS(ConstantBusinessException message, Map<String, String> optionalInfo) {
        super(validateMessage(message).getLogCode());
        this.optionalInfo = (optionalInfo != null)? optionalInfo: new HashMap<>();
        this.metaInfo = new HashMap<>();
        this.constantBusinessException = message;

    }

    public BusinessExceptionECS(ConstantBusinessException message, String optionalInfo) {
        super(validateMessage(message).getLogCode());
        this.optionalInfo = new HashMap<>();
        this.metaInfo = new HashMap<>();
        this.optionalInfo.put (OPTIONAL, (optionalInfo != null) ? optionalInfo : EMPTY);
        this.constantBusinessException = message;
    }

    private static  ConstantBusinessException validateMessage(ConstantBusinessException message) {
        return  (message == null) ? ConstantBusinessException.DEFAULT_EXCEPTION: message;
    }

    public BusinessExceptionECS(ConstantBusinessException message,
                                Map<String, String> optionalInfo,
                                Map<String, String> metaInfo) {
        super(validateMessage(message).getLogCode());
        this.optionalInfo = (optionalInfo != null)? optionalInfo: new HashMap<>();
        this.constantBusinessException = message;
        this.metaInfo = (metaInfo != null)? metaInfo: new HashMap<>();
        var valueMessageId = metaInfo.getOrDefault(MESSAGE_ID, UUID.randomUUID().toString());
        this.metaInfo.put( MESSAGE_ID, valueMessageId);
    }





}
