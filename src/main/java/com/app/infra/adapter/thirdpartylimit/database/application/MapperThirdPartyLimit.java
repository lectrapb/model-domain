package com.app.infra.adapter.thirdpartylimit.database.application;

import com.app.domain.thirdpartylimit.model.ThirdPartyLimit;
import com.app.infra.adapter.thirdpartylimit.database.domain.ThirdPartyLimitData;

import java.util.UUID;

public class MapperThirdPartyLimit {
    public static ThirdPartyLimitData toData(ThirdPartyLimit thirdPartyLimit) {

        return ThirdPartyLimitData.builder()
                .id(thirdPartyLimit.getId().value())
                .documentNumber(thirdPartyLimit.getCustomer().type().getValue())
                .documentType(thirdPartyLimit.getCustomer().number())
                .acronymPartner("BPY")
                .amount(5000)
                .numberOperations(2)
                .build();
    }
}
