package com.kiit.dao_auth.service;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final Map<String, UserDetails> users = new HashMap<>();

    public CustomUserDetailsService() {
        // Dummy user 1
        users.put("admin", User.builder()
                .username("admin")
                .password("{noop}admin123") // {noop} means no encoding
                .authorities("ROLE_ADMIN")
                .build());

        // Dummy user 2
        users.put("user", User.builder()
                .username("user")
                .password("{noop}user123")
                .authorities("ROLE_USER")
                .build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = users.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return user;
    }
}
