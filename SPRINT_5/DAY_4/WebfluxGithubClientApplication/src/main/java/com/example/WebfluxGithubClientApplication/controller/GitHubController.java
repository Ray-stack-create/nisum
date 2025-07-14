package com.example.WebfluxGithubClientApplication.controller;



import com.example.WebfluxGithubClientApplication.service.GitHubClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/github")
public class GitHubController {

    private final GitHubClientService gitHubClientService;

    @Autowired
    public GitHubController(GitHubClientService gitHubClientService) {
        this.gitHubClientService = gitHubClientService;
    }

    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> getUserDetails(@PathVariable String username) {
        return gitHubClientService.getUserDetails(username);
    }
}

