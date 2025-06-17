package com.nisum.product.service;
import com.nisum.product.model.Product;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public boolean updateProduct(int id, Product newProduct) {
        Product existing = getProductById(id);
        if (existing != null) {
            existing.setName(newProduct.getName());
            existing.setPrice(newProduct.getPrice());
            return true;
        }
        return false;
    }

    public boolean deleteProduct(int id) {
        return products.removeIf(p -> p.getId() == id);
    }
}
