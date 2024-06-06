package com.app.domain.share.exception;

public class BusinessException  extends RuntimeException{

    private String customLogMjs;
    private final ConstantBusinessException constantBusinessException;

    public BusinessException(ConstantBusinessException message) {

        super(message.getLogCode());
        this.constantBusinessException = message;

    }

    public BusinessException(ConstantBusinessException message, String customLogMjs) {
        super(message.getErrorCode());
        this.customLogMjs = customLogMjs;
        this.constantBusinessException = message;
    }

    public String getCustomLogMjs() {
        return customLogMjs;
    }

    public ConstantBusinessException getConstantBusinessException() {
        return constantBusinessException;
    }


    public boolean isPresentBusinessException(){
        return constantBusinessException != null;
    }
}
