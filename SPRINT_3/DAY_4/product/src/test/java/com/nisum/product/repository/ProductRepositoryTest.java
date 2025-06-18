package com.nisum.product.repository;
import com.nisum.product.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepo;

    @Test
    void testFindByCategory() {
        Product p = new Product();
        p.setName("TV");
        p.setPrice(BigDecimal.valueOf(500));
        p.setCategory("Electronics");
        productRepo.save(p);

        List<Product> results = productRepo.findByCategory("Electronics");
        assertThat(results).hasSize(1).extracting(Product::getName).contains("TV");
    }

    @Test
    void testFindByPriceBetween() {
        Product p = new Product();
        p.setName("Phone");
        p.setPrice(BigDecimal.valueOf(800));
        p.setCategory("Electronics");
        productRepo.save(p);

        List<Product> results = productRepo.findByPriceBetween(BigDecimal.valueOf(700), BigDecimal.valueOf(900));
        assertThat(results).hasSize(1).extracting(Product::getName).contains("Phone");
    }

    @Test
    void testFindByNameContainingIgnoreCase() {
        Product p = new Product();
        p.setName("Wireless Mouse");
        p.setPrice(BigDecimal.valueOf(20));
        p.setCategory("Accessories");
        productRepo.save(p);

        List<Product> results = productRepo.findByNameContainingIgnoreCase("wireless");
        assertThat(results).hasSize(1);
    }
}
