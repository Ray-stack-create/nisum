package com.kiit.junit.service;

import com.kiit.junit.email.EmailSender;
import com.kiit.junit.model.User;
import com.kiit.junit.repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;
    private final EmailSender emailSender;

    public UserService(UserRepository userRepository, EmailSender emailSender) {
        this.userRepository = userRepository;
        this.emailSender = emailSender;
    }

    public void processUser(String id) {
        User user = userRepository.findUserById(id);

        if (user == null) {
            handleMissingUser(id);
            throw new RuntimeException("User not found");
        }

        emailSender.send(user.getEmail(), "Welcome", "Thanks for joining us.");
        System.out.println("Email sent to: " + user.getEmail());
    }

    public void handleMissingUser(String id) {
        System.out.println("Handling missing user for ID: " + id);
        // fallback logic here
    }
}

