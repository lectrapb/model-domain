package com.app.domain.customlimit.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonetaryLimitCreate {

    private String consumerAcronym;
    private String authorizationToken;
    private Channel channel;
    private Identification identification;
    private Delegate delegate;
    private ThirdPartyRelations thirdPartyRelations;
    private List<Limit> limits;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Channel {
        private String code;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Identification {
        private String type;
        private String number;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Delegate {
        private String type;
        private String number;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ThirdPartyRelations {
        private String businessPartner;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Limit {
        private String transactionCode;
        private String type;
        private double customAmount;
        private String operationsNumber;
    }
}

