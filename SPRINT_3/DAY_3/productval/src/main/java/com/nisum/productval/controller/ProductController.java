package com.nisum.productval.controller;
import com.nisum.productval.model.Product;
import com.nisum.productval.service.ProductService;
import jakarta.validation.Valid;
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
    public String addProduct(@Valid @RequestBody Product product) {
        productService.addProduct(product);
        return "Product added.";
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable int id, @Valid @RequestBody Product product) {
        productService.updateProduct(id, product);
        return "Product updated.";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return "Product deleted.";
    }
}
