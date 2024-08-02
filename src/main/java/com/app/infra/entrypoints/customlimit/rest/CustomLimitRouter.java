package com.app.infra.entrypoints.customlimit.rest;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CustomLimitRouter {

    @Bean
    public RouterFunction<ServerResponse> router2(@Value("/api/search") String url,
                                                 CustomLimitGET handler ){

        return route(GET(url), handler::search);
    }
}
