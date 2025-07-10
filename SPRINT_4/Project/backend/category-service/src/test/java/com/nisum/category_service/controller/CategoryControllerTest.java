package com.nisum.category_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.category_service.model.Category;
import com.nisum.category_service.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    private Category category1;
    private Category category2;

    @BeforeEach
    void setUp() {
        category1 = new Category("Electronics", "electronics.jpg", "Electronics category");
        category1.setId(1);

        category2 = new Category("Books", "books.jpg", "Books category");
        category2.setId(2);
    }

    @Test
    void testGetAllCategories() throws Exception {
        when(categoryService.getAllCategories()).thenReturn(Arrays.asList(category1, category2));

        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Electronics"))
                .andExpect(jsonPath("$[1].name").value("Books"));
    }

    @Test
    void testGetCategoryById_found() throws Exception {
        when(categoryService.getCategoryById(1)).thenReturn(Optional.of(category1));

        mockMvc.perform(get("/categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Electronics"));
    }

    @Test
    void testGetCategoryById_notFound() throws Exception {
        when(categoryService.getCategoryById(99)).thenReturn(Optional.empty());

        mockMvc.perform(get("/categories/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetCategoryByName_found() throws Exception {
        when(categoryService.getCategoryByName("Books")).thenReturn(Optional.of(category2));

        mockMvc.perform(get("/categories/search?name=Books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Books"));
    }

    @Test
    void testGetCategoryByName_notFound() throws Exception {
        when(categoryService.getCategoryByName("Unknown")).thenReturn(Optional.empty());

        mockMvc.perform(get("/categories/search?name=Unknown"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateCategory() throws Exception {
        Category newCategory = new Category("Fashion", "fashion.jpg", "Fashion category");

        Category savedCategory = new Category("Fashion", "fashion.jpg", "Fashion category");
        savedCategory.setId(3);

        when(categoryService.createCategory(any(Category.class))).thenReturn(savedCategory);

        mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCategory)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.name").value("Fashion"));
    }
}
