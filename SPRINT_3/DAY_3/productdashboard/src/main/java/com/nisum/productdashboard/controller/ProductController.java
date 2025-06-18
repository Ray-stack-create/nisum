package com.nisum.productdashboard.controller;
import com.nisum.productdashboard.model.Product;
import com.nisum.productdashboard.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public List<Product> getAllProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String order
    ) {
        return service.getAll(category, minPrice, maxPrice, page, size, sort, order);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public String addProduct(@Valid @RequestBody Product product) {
        service.add(product);
        return "Product added successfully.";
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable int id, @Valid @RequestBody Product product) {
        service.update(id, product);
        return "Product updated successfully.";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        service.delete(id);
        return "Product deleted successfully.";
    }
}
