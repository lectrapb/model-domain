package com.app.infra.entrypoints.customlimit.rest;


import com.app.application.customlimit.MonetaryClientSearch;
import com.app.application.thirdpartylimit.MonetaryLimitsCreator;
import com.app.domain.MonetaryLimitCreate;
import com.app.infra.entrypoints.share.rest.domain.RestResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CustomLimitGET {

    private final MonetaryClientSearch limitCustomSearch;
    private final String URL_TEST = "URL-TEST"; 


    public Mono<ServerResponse> search(ServerRequest request){
        Map<String, String> parameters = new HashMap<>();
        var urlTest = request.headers().asHttpHeaders().getFirst(URL_TEST);
        parameters.put(URL_TEST, urlTest); 
        return limitCustomSearch.searchLimit(parameters)
                .flatMap(o -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON).bodyValue(o));

    }
}
