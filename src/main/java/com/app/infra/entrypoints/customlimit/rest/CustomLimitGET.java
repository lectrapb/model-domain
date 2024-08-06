package com.app.infra.entrypoints.customlimit.rest;


import com.app.application.customlimit.MonetaryClientSearch;
import com.app.infra.adapter.customlimit.rest.domain.ConstantHeader;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class CustomLimitGET {

    private final MonetaryClientSearch limitCustomSearch;
    private final String URL_TEST = "URL-TEST";


    public Mono<ServerResponse> search(ServerRequest request){
        Map<String, String> parameters = new HashMap<>();
        var urlTest = request.headers().asHttpHeaders().getFirst(URL_TEST);
        var message_id = request.headers().asHttpHeaders().getFirst(ConstantHeader.MESSAGE_ID);
        parameters.put(URL_TEST, urlTest);
        parameters.put(ConstantHeader.MESSAGE_ID, message_id);
        return limitCustomSearch.searchLimit(parameters)
                .flatMap(o -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON).bodyValue(o));

    }
}
