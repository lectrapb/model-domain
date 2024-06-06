package com.app.infra.entrypoints.thirdpartylimit.rest;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static  org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
public class ThirdPartyRouter {

    @Bean
    public RouterFunction<ServerResponse> router(@Value("${app.url.monetary.create}") String url,
                                                 ThirdPartyPOST handler ){

        return route(POST(url), handler::create);
    }
}
