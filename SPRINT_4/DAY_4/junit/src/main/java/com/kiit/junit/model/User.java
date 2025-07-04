package com.kiit.junit.model;

import lombok.Data;

@Data
public class User {
    private String id;
    private String name;
    private String email;

    // Constructors, getters, and setters
    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

}
