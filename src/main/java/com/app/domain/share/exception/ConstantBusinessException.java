package com.app.domain.share.exception;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;

public enum ConstantBusinessException implements ErrorManagement{

    MISSING_PARAMETER_REQUEST_EXCEPTION(HTTP_BAD_REQUEST, CodeMjs.MISSING_PARAMETER_BODY,
                                       BusinessCode.BER404_01, "Error en uno de los parametros del body",
                                       LogCode.LOG404_01),

    BAD_DOMAIN_CHANNEL_PARAMETER_REQUEST_EXCEPTION(HTTP_BAD_REQUEST, CodeMjs.MISSING_PARAMETER_BODY,
            BusinessCode.BER404_01, "Error en el formato de tipo de canal por que llego nulo",
            LogCode.LOG404_03),

    BAD_DOMAIN_PARAMETER_REQUEST_EXCEPTION(HTTP_BAD_REQUEST, CodeMjs.MISSING_PARAMETER_BODY,
            BusinessCode.BER404_01, "Error en el formato de tipo de documento",
            LogCode.LOG404_02);



    private final Integer status;
    private final String message;
    private final String errorCode;
    private final String internalMessage;
    private final String logCode;


    ConstantBusinessException(Integer status, String message, String errorCode, String internalMessage, String logCode) {
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
        this.internalMessage = internalMessage;
        this.logCode = logCode;
    }


    private static class BusinessCode {
        public static final String BER404_01 = "ER404-01";
        public static final String BER404_02 = "ER404-02";
        public static final String BER404_03 = "ER404-03";
        public static final String BER404_04 = "ER404-04";

    }

    private static class LogCode {
        public static final String LOG404_01 = "LOG404-01";
        public static final String LOG404_02 = "LOG404-02";
        public static final String LOG404_03 = "LOG404-03";
        public static final String LOG404_04 = "LOG404-04";

    }

    private static class CodeMjs {
        public static final String MISSING_PARAMETER_BODY= "Faltan parametros obligatorios";


    }

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getInternalMessage() {
        return internalMessage;
    }

    @Override
    public String getLogCode() {
        return logCode;
    }
}
