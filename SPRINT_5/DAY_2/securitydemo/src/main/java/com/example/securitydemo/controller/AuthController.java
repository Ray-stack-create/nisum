package com.example.securitydemo.controller;
import com.example.securitydemo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class AuthController {
    private final UserService userService;
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String username, @RequestParam String password) {
        userService.register(username, password);
        return ResponseEntity.ok("User registered successfully");
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        boolean authenticated = userService.authenticate(username, password);
        return authenticated ? ResponseEntity.ok("Login successful") : ResponseEntity.status(401).body("Invalid credentials");
    }
}