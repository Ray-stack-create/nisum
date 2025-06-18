package com.example.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static Long createdProductId;

    @Test
    @Order(1)
    void testCreateProduct() throws Exception {
        Product product = new Product(null, "iPhone", "Smartphone", new BigDecimal("999.99"), 10, "Electronics");

        MvcResult result = mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("iPhone"))
                .andReturn();

        Product created = objectMapper.readValue(result.getResponse().getContentAsString(), Product.class);
        createdProductId = created.getId();
    }

    @Test
    @Order(2)
    void testGetAllProducts() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @Order(3)
    void testGetProductById() throws Exception {
        mockMvc.perform(get("/products/{id}", createdProductId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("iPhone"));
    }

    @Test
    @Order(4)
    void testUpdateProduct() throws Exception {
        Product updated = new Product(null, "iPhone 14", "Latest iPhone", new BigDecimal("1099.99"), 5, "Electronics");

        mockMvc.perform(put("/products/{id}", createdProductId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("iPhone 14"))
                .andExpect(jsonPath("$.stockQuantity").value(5));
    }

    @Test
    @Order(5)
    void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/products/{id}", createdProductId))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(6)
    void testGetDeletedProduct() throws Exception {
        mockMvc.perform(get("/products/{id}", createdProductId))
                .andExpect(status().isNotFound());
    }
}
