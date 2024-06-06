package com.app.application.thirdpartylimit;

import com.app.domain.MonetaryLimitCreate;
import com.app.domain.share.exception.BusinessException;
import com.app.domain.share.exception.ConstantBusinessException;
import com.app.domain.share.labels.UseCase;
import com.app.domain.thirdpartylimit.gateway.MonetaryLimitCreatorGateway;
import com.app.domain.thirdpartylimit.model.Channel;
import com.app.domain.thirdpartylimit.model.ThirdPartyLimit;
import com.app.domain.share.value.StatusLimitAvailable;
import com.app.domain.thirdpartylimit.value.Customer;
import com.app.domain.thirdpartylimit.value.StatusMonetaryLimit;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@UseCase
@AllArgsConstructor
public class MonetaryLimitsCreator {

    private final MonetaryLimitCreatorGateway repository;


    public Mono<Void> addLimit(MonetaryLimitCreate limitCreate){


       return  Mono.fromCallable(() -> new ThirdPartyLimit(
               Customer.of(limitCreate.getIdentification().getType(),
                            limitCreate.getIdentification().getNumber()),
                       new StatusMonetaryLimit(StatusLimitAvailable.ENABLE.name()),
                       new Channel(limitCreate.getChannel().getCode()))
                )

               .flatMap(repository::save);
    }

}



