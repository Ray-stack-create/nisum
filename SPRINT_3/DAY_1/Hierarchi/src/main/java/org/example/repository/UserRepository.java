package org.example.repository;
import org.example.model.User;
import org.example.exception.UserNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private static final Map<Integer, User> users = new HashMap<>();

    static {
        users.put(1, new User(1, "Alice"));
    }

    public User findUserById(int id) {
        User user = users.get(id);
        if (user == null) {
            throw new UserNotFoundException("User with ID " + id + " not found in Repository.");
        }
        return user;
    }
}
