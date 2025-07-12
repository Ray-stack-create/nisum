package com.example.securitydemo.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/unsafe")
public class SqlInjectionDemoController {
    private final EntityManager entityManager;
    public SqlInjectionDemoController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @GetMapping("/search")
    public List<?> search(@RequestParam String keyword) {
        Query query = entityManager.createNativeQuery("SELECT * FROM users WHERE username = '" + keyword + "'");
        return query.getResultList();
    }
}
