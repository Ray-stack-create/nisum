package com.example.demo;

public class Greeter {
    private final String message;
    public Greeter(String message) {
        this.message = message;
    }
    public String greet() {
        return message;
    }
}