package com.app.domain.thirdpartylimit.model;

import com.app.domain.share.exception.BusinessException;
import com.app.domain.share.exception.ConstantBusinessException;

public class Channel  {

    private final String value;

    public Channel(String value) {
        if(value == null){
            throw new BusinessException(ConstantBusinessException.BAD_DOMAIN_CHANNEL_PARAMETER_REQUEST_EXCEPTION);
        }
        this.value = value;
    }

    public String value(){
        return value;
    }
}
