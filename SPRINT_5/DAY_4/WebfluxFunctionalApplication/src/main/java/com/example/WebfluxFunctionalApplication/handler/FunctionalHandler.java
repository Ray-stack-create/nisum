package com.example.WebfluxFunctionalApplication.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.springframework.http.MediaType.TEXT_PLAIN;

@Component
public class FunctionalHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(TEXT_PLAIN)
                .bodyValue("Hello Functional");
    }

    public Mono<ServerResponse> time(ServerRequest request) {
        return ServerResponse.ok()
                .bodyValue(LocalDateTime.now().toString());
    }
}

