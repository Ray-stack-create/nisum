package com.nisum.user.controller;

import com.nisum.user.model.User;
import com.nisum.user.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return repo.save(user);
    }
}

