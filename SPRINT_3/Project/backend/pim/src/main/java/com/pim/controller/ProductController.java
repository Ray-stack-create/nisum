package com.pim.controller;

import com.pim.dto.ProductRequest;
import com.pim.dto.ProductResponse;
import com.pim.model.Product;
import com.pim.model.ProductAttribute;
import com.pim.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<String> addCombinedProduct(@RequestBody @Valid ProductRequest request) {
        String result = productService.addCombinedProduct(request);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Integer id) {
        ProductResponse response = productService.getProductById(id);
        return (response != null) ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam("q") String keyword) {
        return ResponseEntity.ok(productService.searchProducts(keyword));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Integer id, @RequestBody @Valid ProductRequest request) {
        String result = productService.updateProduct(id, request);
        if (result.equals("Product not found.")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/attributes")
    public ResponseEntity<String> addAttributes(@PathVariable Integer id, @RequestBody List<ProductAttribute> attributes) {
        String result = productService.addAttributes(id, attributes);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/attributes")
    public ResponseEntity<List<ProductAttribute>> getAttributes(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.getAttributes(id));
    }
}
