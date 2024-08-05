package com.app.domain.share.model.exception;

import static java.net.HttpURLConnection.*;

public enum ConstantBusinessException implements ErrorManagement{


    DEFAULT_EXCEPTION(HTTP_BAD_REQUEST, CodeMjs.DEFAULT_MESSAGE,
            BusinessCode.BER404_00,  LogMessages.MJS_00,
            LogCode.LOG404_00),

    MISSING_PARAMETER_REQUEST_EXCEPTION(HTTP_BAD_REQUEST, CodeMjs.MISSING_PARAMETER_BODY,
                                       BusinessCode.BER404_01,  LogMessages.MJS_01,
                                       LogCode.LOG404_01),

    BAD_DOMAIN_CHANNEL_PARAMETER_REQUEST_EXCEPTION(HTTP_BAD_REQUEST, CodeMjs.MISSING_PARAMETER_BODY,
            BusinessCode.BER404_01, "Error en el formato de tipo de canal por que llego nulo",
            LogCode.LOG404_03),

    WRONG_ANSWER__REQUEST_EXCEPTION_SUID(HTTP_UNAVAILABLE, CodeMjs.WRONG_ANSWER_EXTERNAL_SERVICE,
            AppCode.APER500_00 , "Error en llamando a SUID getIdentity",
            LogCode.LOG404_03),

    UNKNOWN_EXCEPTION(HTTP_GATEWAY_TIMEOUT, CodeMjs.TIME_OUT_MESSAGE,
            AppCode.APER500_00, "El llamado a un servicio externo por razones desconocidas",
            LogCode.LOG404_04 ),


    BAD_DOMAIN_PARAMETER_REQUEST_EXCEPTION(HTTP_BAD_REQUEST, CodeMjs.MISSING_PARAMETER_BODY,
            BusinessCode.BER404_01, "Error en el formato de tipo de documento",
            LogCode.LOG404_02);


    private final Integer status;
    private final String  message;
    private final String  errorCode;
    private final String  internalMessage;
    private final String  logCode;


    ConstantBusinessException(Integer status, String message, String errorCode, String internalMessage, String logCode) {
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
        this.internalMessage = internalMessage;
        this.logCode = logCode;
    }


    private static class BusinessCode {
        public static final String BER404_00 = "ER404-0";
        public static final String BER404_01 = "ER404-01";
        public static final String BER404_02 = "ER404-02";
        public static final String BER404_03 = "ER404-03";
        public static final String BER404_04 = "ER404-04";

    }

    private static class AppCode {
        public static final String APER500_00 = "500-01";
    }

    private static class LogMessages {

        public static final String MJS_00 = "Dont forget  set this value";
        public static final String MJS_01 = "Error en uno de los parametros del body";
    }

    private static class LogCode {
        public static final String LOG404_00 = "LOG404-000";
        public static final String LOG404_01 = "LOG404-001";
        public static final String LOG404_02 = "LOG404-002";
        public static final String LOG404_03 = "LOG404-003";
        public static final String LOG404_04 = "LOG404-004";

    }

    private static class CodeMjs {
        public static final String DEFAULT_MESSAGE = "Dont forget  set this value";
        public static final String MISSING_PARAMETER_BODY= "Faltan parametros obligatorios";
        public static final String WRONG_ANSWER_EXTERNAL_SERVICE = "Error llamando a un servicio externo";
        public static final String TIME_OUT_MESSAGE= "Retardo en la conexion a servicio externo";


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
