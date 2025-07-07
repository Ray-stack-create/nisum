package com.pim.service;

import com.pim.dto.ProductRequest;
import com.pim.model.Product;
import com.pim.model.ProductAttribute;
import com.pim.repository.ProductAttributeRepository;
import com.pim.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepo;

    @Mock
    private ProductAttributeRepository attributeRepo;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCombinedProduct() {
        ProductRequest req = new ProductRequest();
        req.setProductName("Shirt");
        req.setSku("S001");
        req.setStatus("Active");
        req.setCategory("Clothing");
        req.setColor("Red");

        Product product = new Product();
        product.setProductId(1);
        when(productRepo.save(any(Product.class))).thenReturn(product);

        String result = productService.addCombinedProduct(req);

        assertEquals("Product and attributes added successfully.", result);
        verify(productRepo).save(any(Product.class));
        verify(attributeRepo).saveAll(anyList());
    }

    @Test
    void testGetProductById_ProductFound() {
        Product product = new Product();
        product.setProductId(1);
        product.setProductName("Shirt");
        when(productRepo.findById(1)).thenReturn(Optional.of(product));
        when(attributeRepo.findByProduct_ProductId(1)).thenReturn(List.of());

        var result = productService.getProductById(1);
        assertNotNull(result);
        assertEquals("Shirt", result.getProductName());
    }

    @Test
    void testDeleteProduct() {
        productService.deleteProduct(1);
        verify(productRepo).deleteById(1);
    }

    @Test
    void testSearchProducts() {
        when(productRepo.searchByKeyword("Shirt")).thenReturn(List.of(new Product()));
        List<Product> result = productService.searchProducts("Shirt");
        assertEquals(1, result.size());
    }
}

