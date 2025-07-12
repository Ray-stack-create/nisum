package com.example.service_b.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {

    @GetMapping("/secure-endpoint")
    public ResponseEntity<String> secureData(Authentication auth) {
        return ResponseEntity.ok("Hello " + auth.getName() + ", you are authorized as a microservice.");
    }
}

