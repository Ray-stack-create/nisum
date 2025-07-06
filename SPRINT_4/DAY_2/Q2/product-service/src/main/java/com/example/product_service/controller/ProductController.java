package com.example.product_service.controller;

import com.example.product_service.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> getProducts() {
        return List.of(
                new Product(1, "Laptop", 1000),
                new Product(2, "Smartphone", 600)
        );
    }
}
