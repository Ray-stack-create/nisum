package com.pim.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pim.dto.ProductRequest;
import com.pim.dto.ProductResponse;
import com.pim.model.Product;
import com.pim.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddCombinedProduct() throws Exception {
        ProductRequest request = new ProductRequest();
        request.setProductName("Shirt");
        request.setSku("SKU1");
        request.setStatus("Active");
        request.setCategory("Clothing");

        Mockito.when(productService.addCombinedProduct(Mockito.any()))
                .thenReturn("Product and attributes added successfully.");

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Product and attributes added successfully."));
    }

    @Test
    void testGetAllProducts() throws Exception {
        Mockito.when(productService.getAllProducts()).thenReturn(List.of(new Product()));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testGetProductById() throws Exception {
        ProductResponse response = new ProductResponse();
        response.setProductId(1);
        response.setProductName("Shirt");

        Mockito.when(productService.getProductById(1)).thenReturn(response);

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("Shirt"));
    }

    @TestConfiguration
    static class MockServiceConfig {
        @Bean
        ProductService productService() {
            return Mockito.mock(ProductService.class);
        }
    }

}

