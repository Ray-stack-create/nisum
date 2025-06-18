package com.nisum.product_exception.service;
import com.nisum.product_exception.model.Product;
import com.nisum.product_exception.exception.ProductNotFoundException;
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
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void updateProduct(int id, Product newProduct) {
        Product product = getProductById(id);
        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
    }

    public void deleteProduct(int id) {
        Product product = getProductById(id);
        products.remove(product);
    }
}
