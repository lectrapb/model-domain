package com.app.domain.thirdpartylimit.value;

import com.app.domain.share.model.exception.BusinessException;
import com.app.domain.share.model.exception.ConstantBusinessException;
import com.app.domain.share.value.TypeDocumentAvailable;
import lombok.Getter;

@Getter
public class TypeDocument {

    private final  String value;

    public TypeDocument(String value) {
        if(value == null || value.isEmpty()){
            throw new BusinessException(ConstantBusinessException.MISSING_PARAMETER_REQUEST_EXCEPTION);
        }else{
            try{
               TypeDocumentAvailable.valueOf(value);
            }catch (IllegalArgumentException ex){
                throw new BusinessException(ConstantBusinessException.BAD_DOMAIN_PARAMETER_REQUEST_EXCEPTION);
            }
        }
        this.value = value;
    }

}
