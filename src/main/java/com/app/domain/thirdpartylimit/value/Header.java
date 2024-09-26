package com.app.domain.thirdpartylimit.value;

import com.app.domain.share.model.exception.ConstantBusinessException;
import com.app.domain.share.model.exception.ecs.BusinessExceptionECS;

public class Header {

    private String value;

    public Header(String value) {
        if(value == null){
            throw new BusinessExceptionECS(ConstantBusinessException.DEFAULT_EXCEPTION);
        }
        this.value = value;
    }

    public Header(String value, String origin) {
        if(value == null){
            throw new BusinessExceptionECS(ConstantBusinessException.DEFAULT_EXCEPTION);
        }
        this.value = value;
    }
}
