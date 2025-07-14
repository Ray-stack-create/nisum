package com.example.WebfluxGreetApplication.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(GreetController.class)
public class GreetControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testGreetEndpoint() {
        webTestClient.get()
                .uri("/greet")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("Hello WebFlux");
    }
}
