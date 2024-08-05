package com.app.infra.entrypoints.thirdpartylimit.rest;


import com.app.application.thirdpartylimit.MonetaryLimitsCreator;
import com.app.domain.share.model.MonetaryLimitCreate;
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

    private final MonetaryLimitsCreator limitCreate;



    public Mono<ServerResponse> create(ServerRequest request){

        return request.bodyToMono(MonetaryLimitCreate.class)
                .flatMap(limitCreate::addLimit)
                .map(Optional::of)
                .defaultIfEmpty(Optional.empty())
                .flatMap(opt -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(RestResponse.okCommand()), RestResponse.class));

    }
}
