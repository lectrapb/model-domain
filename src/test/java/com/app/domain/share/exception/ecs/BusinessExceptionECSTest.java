package com.app.domain.share.exception.ecs;

import com.app.domain.share.common.model.exception.ConstantBusinessException;
import com.app.domain.share.common.model.exception.ecs.BusinessExceptionECS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BusinessExceptionECSTest {

    private static final String EMPTY = "";
    private static final String MESSAGE = "message";
    private static final String OPTIONAL_INFO_STRING = "optionalInfoString";

    @BeforeEach
    void setUp() {
    }

    @Test
    void businessExceptionTest() {

        var businessException = new BusinessExceptionECS(MESSAGE);
        assertNotNull(businessException);
    }

    @Test
    void businessExceptionConstantTest() {

        var message =  ConstantBusinessException.DEFAULT_EXCEPTION;
        var businessException = new BusinessExceptionECS(message);
        assertNotNull(businessException);
    }

    @Test
    void businessExceptionExceptionOptionalInfoTest() {

        ConstantBusinessException message = ConstantBusinessException.DEFAULT_EXCEPTION;
        var businessException = new BusinessExceptionECS(message, OPTIONAL_INFO_STRING);
        assertNotNull(businessException);
    }

    @Test
    void businessExceptionExceptionOptionalInfoNullTest() {

        ConstantBusinessException message = ConstantBusinessException.DEFAULT_EXCEPTION;
        String optionalInfo = null;
        var businessException = new BusinessExceptionECS(message, optionalInfo);
        assertNotNull(businessException);
    }

    @Test
    void businessExceptionOptionalMaoTest(){
        ConstantBusinessException message = ConstantBusinessException.DEFAULT_EXCEPTION;
        var optionalInfo = Map.of(EMPTY , EMPTY );
        var businessException = new BusinessExceptionECS(message, optionalInfo);
        assertNotNull(businessException);
    }

    @Test
    void businessExceptionOptionalMapAndMetaInfo(){

        ConstantBusinessException message = ConstantBusinessException.DEFAULT_EXCEPTION;

        var meta = BusinessExceptionECS.MetaInfo.builder()
                .messageId(UUID.randomUUID().toString())
                .build();
        var businessException = new BusinessExceptionECS(message,  OPTIONAL_INFO_STRING, meta);
        assertNotNull(businessException);
    }

    @Test
    void businessExceptionOptionalMapAndMetaNullInfoTest(){

        ConstantBusinessException message = ConstantBusinessException.DEFAULT_EXCEPTION;
        String optionalInfo = null;
        BusinessExceptionECS.MetaInfo meta = null;
        var businessException = new BusinessExceptionECS(message, optionalInfo, meta);
        assertNotNull(businessException);
    }

    @Test
    void businessExceptionOptionalMapAndMetaInfoMapTest(){

        ConstantBusinessException message = ConstantBusinessException.DEFAULT_EXCEPTION;
        Map<String, String> optionalInfo = Map.of(EMPTY , EMPTY );
        var meta = BusinessExceptionECS.MetaInfo.builder().build();
        var businessException = new BusinessExceptionECS(message, optionalInfo, meta);
        assertNotNull(businessException);
    }

    @Test
    void businessExceptionOptionalMapAndMetaInfoMapNullTest(){

        ConstantBusinessException message = null;
        Map<String, String> optionalInfo = Map.of(EMPTY , EMPTY );
        BusinessExceptionECS.MetaInfo meta = null;
        var businessException = new BusinessExceptionECS(message, optionalInfo, meta);
        assertNotNull(businessException);
    }


}