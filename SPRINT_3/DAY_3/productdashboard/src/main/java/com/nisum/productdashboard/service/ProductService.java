package com.nisum.productdashboard.service;
import com.nisum.productdashboard.model.Product;
import com.nisum.productdashboard.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
        import java.util.stream.Collectors;

@Service
public class ProductService {
    private static final List<Product> products = new ArrayList<>();
    private static int idCounter = 1;

    public List<Product> getAll(String category, Double minPrice, Double maxPrice, int page, int size, String sortBy, String order) {
        Comparator<Product> comparator = switch (sortBy) {
            case "price" -> Comparator.comparing(Product::getPrice);
            case "name" -> Comparator.comparing(Product::getName);
            default -> Comparator.comparing(Product::getId);
        };

        if ("desc".equalsIgnoreCase(order)) comparator = comparator.reversed();

        return products.stream()
                .filter(p -> category == null || p.getCategory().equalsIgnoreCase(category))
                .filter(p -> minPrice == null || p.getPrice() >= minPrice)
                .filter(p -> maxPrice == null || p.getPrice() <= maxPrice)
                .sorted(comparator)
                .skip((page - 1) * size)
                .limit(size)
                .collect(Collectors.toList());
    }

    public Product getById(int id) {
        return products.stream().filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product not found: " + id));
    }

    public void add(Product product) {
        product.setId(idCounter++);
        products.add(product);
    }

    public void update(int id, Product updated) {
        Product existing = getById(id);
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setPrice(updated.getPrice());
        existing.setStockQuantity(updated.getStockQuantity());
        existing.setCategory(updated.getCategory());
    }

    public void delete(int id) {
        Product product = getById(id);
        products.remove(product);
    }
}

