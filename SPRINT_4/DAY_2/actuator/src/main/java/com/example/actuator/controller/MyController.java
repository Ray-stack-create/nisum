package com.example.actuator.controller;

import com.example.actuator.service.HitCounterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private final HitCounterService counterService;

    public MyController(HitCounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping("/hello")
    public String hello() {
        counterService.increment();
        return "Hello, World!";
    }
}

