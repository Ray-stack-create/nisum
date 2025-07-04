package com.example.product.controller;

import com.example.product.model.Product;
import com.example.product.model.UserDTO;
import com.example.product.repository.ProductRepository;
import com.example.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepo;
    private final ProductService userService;

    public ProductController(ProductRepository productRepo, ProductService userService) {
        this.productRepo = productRepo;
        this.userService = userService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        return productRepo.save(product);
    }

    @GetMapping("/{id}/users")
    public UserDTO getUserDetailsForProduct(@PathVariable Long id) {
        Product product = productRepo.findById(id).orElse(null);
        if (product != null) {
            return userService.getUserById(product.getUserId());
        }
        return null;
    }
}
