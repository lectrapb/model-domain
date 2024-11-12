package com.app.application;

import com.app.application.thirdpartylimit.MonetaryLimitsCreator;
import com.app.domain.share.common.model.cqrs.Command;
import com.app.domain.share.common.model.cqrs.ContextData;
import com.app.domain.share.model.cqrs.MonetaryLimitCreate;
import com.app.domain.thirdpartylimit.gateway.MonetaryLimitCreatorGateway;
import com.app.domain.share.common.value.TypeDocumentAvailable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MonetaryLimitsCreatorTest {


    private MonetaryLimitCreatorGateway repository;
    private MonetaryLimitsCreator useCase;

    @BeforeEach
    void setUp() {
        repository = mock(MonetaryLimitCreatorGateway.class);
        useCase = new MonetaryLimitsCreator(repository);
    }

    @Test
    @Disabled
    void addLimit_null_test() {
        //Given
        var identification = new MonetaryLimitCreate.Identification("", "");
        var context = ContextData.builder().build();
        var request = MonetaryLimitCreate.builder()
                .identification(new MonetaryLimitCreate.Identification(identification.getType(), identification.getNumber()))
                .build();
        //When
        //Then
      useCase.addLimit(new Command<>(request,context))
                .as(StepVerifier::create)
                .verifyComplete();

    }


    @Test
    void addLimit_ok_Test() {
        //Given
        var acronym = "BPY";
        var authorizationCode = "fdfdfd";
        var identification = new MonetaryLimitCreate.Identification(TypeDocumentAvailable.CC.getValue(), "3232434343");
        var channel = new MonetaryLimitCreate.Channel("BPY");
        var delegate = new MonetaryLimitCreate.Delegate(TypeDocumentAvailable.CC.getValue(), "3232323232");
        var thirdPartyRelations = new MonetaryLimitCreate.ThirdPartyRelations("fdfdfsfsfdsdfsd");
        List<MonetaryLimitCreate.Limit> limits = List.of(MonetaryLimitCreate.Limit.builder()
                        .transactionCode("1000")
                        .type("fdfdffd")
                        .customAmount(1000.0)
                        .operationsNumber("dfdfdfdf")
                        .build());
        var request = MonetaryLimitCreate.builder()
                .consumerAcronym(acronym)
                .authorizationToken(authorizationCode)
                .channel(channel)
                .identification(identification)
                .delegate(delegate)
                .thirdPartyRelations(thirdPartyRelations)
                .limits(limits)
                .build();
        var context = ContextData.builder().build();
        //When
        when(repository.save(any())).thenReturn(Mono.empty());

        //Then
        useCase.addLimit(new Command<>(request,context))
                .as(StepVerifier::create)
                .verifyComplete();
    }
}