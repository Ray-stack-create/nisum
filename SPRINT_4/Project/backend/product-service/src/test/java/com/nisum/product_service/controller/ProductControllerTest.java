package com.nisum.product_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.product_service.dto.*;
import com.nisum.product_service.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    private ProductDto productDto;

    @BeforeEach
    void setUp() {
        productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Test Product");
    }


    @Test
    void testGetAllProductIds() throws Exception {
        List<Long> ids = List.of(1L, 2L);
        when(productService.getAllProductIds()).thenReturn(ids);

        mockMvc.perform(get("/products/ids"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value(1L));
    }

    @Test
    void testGetProductById() throws Exception {
        when(productService.getProductById(1L)).thenReturn(productDto);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Product"));
    }

    @Test
    void testGetProductSizes() throws Exception {
        List<String> sizes = List.of("S", "M");
        when(productService.getProductSizes(1L)).thenReturn(sizes);

        mockMvc.perform(get("/products/1/sizes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("S"));
    }

    @Test
    void testGetProductSkus() throws Exception {
        List<String> skus = List.of("SKU123");
        when(productService.getProductSkus(1L, "M")).thenReturn(skus);

        mockMvc.perform(get("/products/1/skus?size=M"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("SKU123"));
    }

    @Test
    void testGetProductSeller() throws Exception {
        SellerDto sellerDto = new SellerDto();
        sellerDto.setId(100L);
        sellerDto.setSellerName("Seller A");

        when(productService.getProductSellerDetails(1L)).thenReturn(sellerDto);

        mockMvc.perform(get("/products/1/sellers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Seller A"));
    }

    @Test
    void testCreateProduct() throws Exception {
        when(productService.createProduct(any())).thenReturn(productDto);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Product"));
    }

    @Test
    void testUpdateProduct() throws Exception {
        when(productService.updateProduct(eq(1L), any())).thenReturn(productDto);

        mockMvc.perform(put("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Product"));
    }

    @Test
    void testDeleteProductSuccess() throws Exception {
        when(productService.deleteProduct(1L)).thenReturn(true);

        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteProductNotFound() throws Exception {
        when(productService.deleteProduct(1L)).thenReturn(false);

        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testImportProductsSuccess() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "products.csv", "text/csv", "data".getBytes());

        doNothing().when(productService).importProducts(file);

        mockMvc.perform(multipart("/products/import").file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Products imported successfully"));
    }

    @Test
    void testImportProductsFailure() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "products.csv", "text/csv", "data".getBytes());

        doThrow(new RuntimeException("Invalid format")).when(productService).importProducts(file);

        mockMvc.perform(multipart("/products/import").file(file))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Import failed: Invalid format"));
    }
}

