package com.example.user_service.controller;

import com.example.user_service.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/users")
    public List<User> getUsers() {
        return List.of(
                new User(1, "Sankha"),
                new User(2, "Ananya")
        );
    }
}
