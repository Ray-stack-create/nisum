package com.example.WebfluxFunctionalApplication.router;


import com.example.WebfluxFunctionalApplication.handler.FunctionalHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class FunctionalRouter {

    @Bean
    public RouterFunction<?> routes(FunctionalHandler handler) {
        return route(GET("/hello"), handler::hello)
                .andRoute(GET("/time"), handler::time);
    }
}

