package com.app.domain.share.exception.ecs;

import com.app.domain.share.exception.ConstantBusinessException;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

@Getter
@Log4j2
public class BusinessExceptionECS extends RuntimeException {

    private final Map<String, String> optionalInfo;
    private final ConstantBusinessException constantBusinessException;
    private static final String EMPTY = "";
    private static final String OPTIONAL = "OPTIONAL";

    public BusinessExceptionECS(ConstantBusinessException message) {
        this(message, EMPTY);
    }

    public BusinessExceptionECS(ConstantBusinessException message, Map<String, String> optionalInfo) {
        super(validateMessage(message).getLogCode());
        this.optionalInfo = (optionalInfo != null)? optionalInfo: new HashMap<>();
        this.constantBusinessException = message;
    }

    public BusinessExceptionECS(ConstantBusinessException message, String optionalInfo) {
        super(validateMessage(message).getLogCode());
        this.optionalInfo = new HashMap<>();
        this.optionalInfo.put (OPTIONAL, (optionalInfo != null) ? optionalInfo : EMPTY);
        this.constantBusinessException = message;
    }

    private static  ConstantBusinessException validateMessage(ConstantBusinessException message) {
        return  (message == null) ? ConstantBusinessException.DEFAULT_EXCEPTION: message;
    }





}
