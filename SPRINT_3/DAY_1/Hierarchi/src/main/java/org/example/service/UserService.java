package org.example.service;


import org.example.model.User;
import org.example.repository.UserRepository;

public class UserService {
    private final UserRepository repository = new UserRepository();

    public User getUserById(int id) {
        // No catch block here - error bubbles up
        return repository.findUserById(id);
    }
}
