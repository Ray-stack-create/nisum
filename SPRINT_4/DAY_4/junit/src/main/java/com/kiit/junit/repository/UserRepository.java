package com.kiit.junit.repository;

import com.kiit.junit.model.User;

public interface UserRepository {
    User findUserById(String id);
}

