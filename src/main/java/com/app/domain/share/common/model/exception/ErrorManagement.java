package com.app.domain.share.common.model.exception;

public interface ErrorManagement {

    Integer getStatus() ;
    String getMessage();
    String getErrorCode();
    String getInternalMessage();
    String getLogCode();
}
