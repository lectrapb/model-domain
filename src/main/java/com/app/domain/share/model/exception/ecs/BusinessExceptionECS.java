package com.app.domain.share.model.exception.ecs;

import com.app.domain.share.model.exception.ConstantBusinessException;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Log4j2
public class BusinessExceptionECS extends RuntimeException {

    private  ConstantBusinessException constantBusinessException = ConstantBusinessException.DEFAULT_EXCEPTION;
    private  Map<String, String> optionalInfo = new HashMap<>();
    private  MetaInfo metaInfo;
    private static final String EMPTY = "";
    private static final String OPTIONAL = "OPTIONAL";


    public BusinessExceptionECS(String message){
        super(message);
    }

    public BusinessExceptionECS(ConstantBusinessException message) {
        this(message, EMPTY);
        this.metaInfo = MetaInfo.builder().build();
    }

    public BusinessExceptionECS(ConstantBusinessException message,  MetaInfo metaInfo) {
        super(validateMessage(message).getLogCode());
        this.constantBusinessException = validateMessage(message);
        this.optionalInfo.put (OPTIONAL,  EMPTY);
        this.metaInfo = MetaInfo.builder().build();
    }

    public BusinessExceptionECS(ConstantBusinessException message, String optionalInfo) {
        super(validateMessage(message).getLogCode());
        this.constantBusinessException = validateMessage(message);
        this.optionalInfo.put (OPTIONAL, (optionalInfo != null) ? optionalInfo : EMPTY);
        this.metaInfo = MetaInfo.builder().build();
    }

    public BusinessExceptionECS(ConstantBusinessException message,  Map<String, String> optionalInfo) {
        super(validateMessage(message).getLogCode());
        this.constantBusinessException = validateMessage(message);
        this.optionalInfo = optionalInfo;
        this.metaInfo = MetaInfo.builder().build();
    }

    public BusinessExceptionECS(ConstantBusinessException message, String optionalInfo,  MetaInfo metaInfo) {
        super(validateMessage(message).getLogCode());
        this.constantBusinessException = validateMessage(message);
        this.optionalInfo.put (OPTIONAL, (optionalInfo != null) ? optionalInfo : EMPTY);
        this.metaInfo = (metaInfo != null)? metaInfo: MetaInfo.builder().build();

    }


    public BusinessExceptionECS(ConstantBusinessException message, Map<String, String> optionalInfo, MetaInfo metaInfo) {
        super(validateMessage(message).getLogCode());
        this.constantBusinessException = validateMessage(message);
        this.optionalInfo = optionalInfo;
        this.metaInfo = (metaInfo != null)? metaInfo: MetaInfo.builder().build();

    }

    private static  ConstantBusinessException validateMessage(ConstantBusinessException message) {
        return  (message == null) ? ConstantBusinessException.DEFAULT_EXCEPTION: message;
    }

     @Builder
     @Getter
     public static class MetaInfo{

         @Builder.Default
         private final String messageId = UUID.randomUUID().toString();

    }







}
