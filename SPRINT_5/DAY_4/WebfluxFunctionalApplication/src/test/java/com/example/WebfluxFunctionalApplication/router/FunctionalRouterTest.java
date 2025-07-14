package com.example.WebfluxFunctionalApplication.router;

import com.example.WebfluxFunctionalApplication.handler.FunctionalHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest
@Import({FunctionalRouter.class, FunctionalHandler.class})
public class FunctionalRouterTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testHelloRoute() {
        webTestClient.get()
                .uri("/hello")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("Hello Functional");
    }

    @Test
    void testTimeRoute() {
        webTestClient.get()
                .uri("/time")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(response -> {
                    String timestamp = response.getResponseBody();
                    assert timestamp != null && timestamp.contains("T");
                });
    }
}

