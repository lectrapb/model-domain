package com.app.domain.thirdpartylimit.value;

import com.app.domain.share.model.exception.BusinessException;
import com.app.domain.share.model.exception.ConstantBusinessException;
import com.app.domain.share.value.StatusLimitAvailable;
import lombok.Data;

@Data
public class StatusMonetaryLimit {

    private String value;

    public StatusMonetaryLimit(String value) {

        if(value == null || value.isEmpty()){
            throw new BusinessException(ConstantBusinessException.MISSING_PARAMETER_REQUEST_EXCEPTION);
        }else {
            try{
                StatusLimitAvailable.valueOf(value);
            }catch (IllegalArgumentException ex){
                throw new BusinessException(ConstantBusinessException.BAD_DOMAIN_PARAMETER_REQUEST_EXCEPTION);
            }
        }
        this.value = value;
    }
}
