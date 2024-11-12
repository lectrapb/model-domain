package com.app.infra.entrypoints.thirdpartylimit.rest;


import com.app.application.thirdpartylimit.MonetaryLimitsCreator;
import com.app.application.thirdpartylimit.ThirdLimitCreateCommand;
import com.app.domain.customlimit.model.MonetaryLimitCreate;
import com.app.domain.share.bus.command.CommandBus;
import com.app.domain.share.common.model.cqrs.Command;
import com.app.domain.share.common.model.cqrs.ContextData;
import com.app.infra.adapter.customlimit.rest.domain.ConstantHeader;
import com.app.infra.entrypoints.share.rest.domain.RestResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ThirdPartyPOST {

 //   private final MonetaryLimitsCreator limitCreate;
    private final CommandBus commandBus;



//    public Mono<ServerResponse> create(ServerRequest request){
//
//        var messageId = request.headers().asHttpHeaders().getFirst(ConstantHeader.MESSAGE_ID);
//        var contextData = ContextData.builder().messageId(messageId).build();
//
//        return request.bodyToMono(MonetaryLimitCreate.class)
//                .flatMap(monetaryLimitCreate -> limitCreate
//                        .addLimit(new Command<>(monetaryLimitCreate,contextData)))
//                .map(Optional::of)
//                .defaultIfEmpty(Optional.empty())
//                .flatMap(opt -> ServerResponse.status(HttpStatus.CREATED)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(Mono.just(RestResponse.okCommand()), RestResponse.class));
//
//    }


    public Mono<ServerResponse> create(ServerRequest request){

        var messageId = request.headers().asHttpHeaders().getFirst(ConstantHeader.MESSAGE_ID);
        var contextData = ContextData.builder().messageId(messageId).build();

        return request.bodyToMono(MonetaryLimitCreate.class)
                .flatMap(monetaryLimitCreate -> commandBus.dispatch(new
                        ThirdLimitCreateCommand( monetaryLimitCreate,contextData)))
                .map(Optional::of)
                .defaultIfEmpty(Optional.empty())
                .flatMap(opt -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(RestResponse.okCommand()), RestResponse.class));

    }
}
