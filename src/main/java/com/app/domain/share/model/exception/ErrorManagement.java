package com.app.domain.share.model.exception;

public interface ErrorManagement {

    Integer getStatus() ;
    String getMessage();
    String getErrorCode();
    String getInternalMessage();
    String getLogCode();
}
