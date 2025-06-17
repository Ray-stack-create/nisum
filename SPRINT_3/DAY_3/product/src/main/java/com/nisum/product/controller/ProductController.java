package com.nisum.product.controller;
import com.nisum.product.model.Product;
import com.nisum.product.service.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService service) {
        this.productService = service;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public String addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return "Product added.";
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable int id, @RequestBody Product product) {
        if (productService.updateProduct(id, product)) {
            return "Product updated.";
        } else {
            return "Product not found.";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        if (productService.deleteProduct(id)) {
            return "Product deleted.";
        } else {
            return "Product not found.";
        }
    }
}
