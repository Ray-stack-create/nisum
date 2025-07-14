package com.example.WebfluxNumbersApplication.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;
import reactor.core.publisher.Flux;

@WebFluxTest(NumberStreamController.class)
public class NumberStreamControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testNumberStream() {
        Flux<Integer> responseBody = webTestClient.get()
                .uri("/numbers")
                .exchange()
                .expectStatus().isOk()
                .returnResult(Integer.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectNext(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .verifyComplete();
    }
}

