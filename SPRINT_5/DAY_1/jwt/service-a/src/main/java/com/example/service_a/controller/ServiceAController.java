package com.example.service_a.controller;

import com.example.service_a.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceAController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/call-service-b")
    public ResponseEntity<String> callServiceB() {
        return clientService.callServiceB();
    }
}

