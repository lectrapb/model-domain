package com.app.infra.entrypoints.thirdpartylimit.rest;


import com.app.application.thirdpartylimit.MonetaryLimitsCreator;
import com.app.domain.MonetaryLimitCreate;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
                        .body(Mono.just("Complete"), String.class));

    }
}
