package com.nisum.category_service.service;
import com.nisum.category_service.model.Category;
import com.nisum.category_service.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category category1;
    private Category category2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        category1 = new Category("Electronics", "electronics.jpg", "Electronics category");
        category1.setId(1);

        category2 = new Category("Books", "books.jpg", "Books category");
        category2.setId(2);
    }

    @Test
    void testGetAllCategories() {
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category1, category2));

        List<Category> categories = categoryService.getAllCategories();

        assertEquals(2, categories.size());
        assertEquals("Electronics", categories.get(0).getName());
    }

    @Test
    void testGetCategoryById_found() {
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category1));

        Optional<Category> result = categoryService.getCategoryById(1);

        assertTrue(result.isPresent());
        assertEquals("Electronics", result.get().getName());
    }

    @Test
    void testGetCategoryById_notFound() {
        when(categoryRepository.findById(99)).thenReturn(Optional.empty());

        Optional<Category> result = categoryService.getCategoryById(99);

        assertFalse(result.isPresent());
    }

    @Test
    void testGetCategoryByName_found() {
        when(categoryRepository.findByNameIgnoreCase("Books")).thenReturn(Optional.of(category2));

        Optional<Category> result = categoryService.getCategoryByName("Books");

        assertTrue(result.isPresent());
        assertEquals("Books", result.get().getName());
    }

    @Test
    void testGetCategoryByName_notFound() {
        when(categoryRepository.findByNameIgnoreCase("Unknown")).thenReturn(Optional.empty());

        Optional<Category> result = categoryService.getCategoryByName("Unknown");

        assertFalse(result.isPresent());
    }

    @Test
    void testCreateCategory() {
        Category newCategory = new Category("Fashion", "fashion.jpg", "Fashion category");
        newCategory.setId(3);

        when(categoryRepository.save(any(Category.class))).thenReturn(newCategory);

        Category saved = categoryService.createCategory(newCategory);

        assertEquals("Fashion", saved.getName());
        assertEquals("fashion.jpg", saved.getCategoryImage());
        verify(categoryRepository).save(newCategory);
    }
}
