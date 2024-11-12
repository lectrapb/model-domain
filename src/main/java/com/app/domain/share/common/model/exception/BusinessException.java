package com.app.domain.share.common.model.exception;

import com.app.domain.share.common.model.exception.ecs.BusinessExceptionECS;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

@Log4j2
public class BusinessException extends BusinessExceptionECS {


    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(ConstantBusinessException message) {
        super(message);
    }

    public BusinessException(ConstantBusinessException message, String optionalInfo) {
        super(message, optionalInfo);
    }

    public BusinessException(ConstantBusinessException message,  MetaInfo metaInfo) {
        super(message,metaInfo);
    }

    public BusinessException(ConstantBusinessException message, String optionalInfo, String messageId) {
        super(message, optionalInfo, BusinessExceptionECS.MetaInfo.builder().messageId(messageId).build());
    }

    public BusinessException(ConstantBusinessException message, Map<String, String> optionalInfo) {
        super(message, optionalInfo);
    }

    public BusinessException(ConstantBusinessException message, Map<String, String> optionalInfo, String messageId) {
        super(message, optionalInfo,  BusinessExceptionECS.MetaInfo.builder().messageId(messageId).build());
    }

    public BusinessException(ConstantBusinessException message, String optionalInfo, MetaInfo metaInfo) {
        super(message, optionalInfo, metaInfo);
    }







}
