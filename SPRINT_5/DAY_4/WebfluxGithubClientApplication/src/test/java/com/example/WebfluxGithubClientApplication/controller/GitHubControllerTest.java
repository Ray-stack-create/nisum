package com.example.WebfluxGithubClientApplication.controller;

import com.example.WebfluxGithubClientApplication.service.GitHubClientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;

@WebFluxTest(GitHubController.class)
public class GitHubControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private GitHubClientService gitHubClientService;

    @Test
    void testGetUserDetails() {
        Mockito.when(gitHubClientService.getUserDetails("octocat"))
                .thenReturn(Mono.just("{\"login\":\"octocat\"}"));

        webTestClient.get().uri("/github/octocat")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("{\"login\":\"octocat\"}");
    }
}

