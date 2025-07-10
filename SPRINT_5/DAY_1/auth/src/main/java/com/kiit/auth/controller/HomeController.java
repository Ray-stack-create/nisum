package com.kiit.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to the public homepage!";
    }

    @GetMapping("/user")
    public String user() {
        return "Hello USER!";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hello ADMIN!";
    }
}
