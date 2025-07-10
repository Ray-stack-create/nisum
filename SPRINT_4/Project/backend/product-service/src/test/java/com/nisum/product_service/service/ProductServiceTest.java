package com.nisum.product_service.service;

import com.nisum.product_service.client.CategoryServiceClient;
import com.nisum.product_service.dto.*;
import com.nisum.product_service.exception.CategoryNotFoundException;
import com.nisum.product_service.exception.ProductNotFoundException;
import com.nisum.product_service.model.Product;
import com.nisum.product_service.model.ProductAttribute;
import com.nisum.product_service.model.Seller;
import com.nisum.product_service.repository.ProductRepository;
import com.nisum.product_service.repository.SellerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryServiceClient categoryServiceClient;

    @Mock
    private SellerRepository sellerRepository;

    @Mock
    private CsvParserService csvParserService;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProductById_success() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setCategoryId(10L);
        product.setSellerId(5L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        CategoryDto categoryDto = new CategoryDto(10L, "Electronics", "image.jpg", "Electronics category");
        when(categoryServiceClient.getCategoryById(10L)).thenReturn(categoryDto);

        ProductDto dto = productService.getProductById(1L);

        assertEquals("Test Product", dto.getName());
        assertEquals("Electronics", dto.getCategoryName());
    }

    @Test
    void testGetProductById_notFound() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(99L));
    }

    @Test
    void testCreateProduct_success() {
        ProductDto productDto = new ProductDto();
        productDto.setName("New Product");
        productDto.setCategoryId(2L);
        productDto.setSellerId(1L);
        productDto.setStatus("Active");

        CategoryDto categoryDto = new CategoryDto(2L, "Books", "book.jpg", "Book category");
        when(categoryServiceClient.getCategoryById(2L)).thenReturn(categoryDto);

        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> {
            Product p = invocation.getArgument(0);
            p.setId(1L); // Simulate DB-generated ID
            return p;
        });

        ProductDto created = productService.createProduct(productDto);

        assertEquals("New Product", created.getName());
        assertEquals("Active", created.getStatus());
    }

    @Test
    void testCreateProduct_invalidCategory() {
        ProductDto productDto = new ProductDto();
        productDto.setName("Invalid Category Product");
        productDto.setCategoryId(99L);
        productDto.setSellerId(1L);

        when(categoryServiceClient.getCategoryById(99L)).thenReturn(null);

        assertThrows(CategoryNotFoundException.class, () -> productService.createProduct(productDto));
    }

    @Test
    void testDeleteProduct_success() {
        when(productRepository.existsById(1L)).thenReturn(true);
        doNothing().when(productRepository).deleteById(1L);

        boolean result = productService.deleteProduct(1L);
        assertTrue(result);
        verify(productRepository).deleteById(1L);
    }

    @Test
    void testDeleteProduct_notFound() {
        when(productRepository.existsById(1L)).thenReturn(false);
        assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(1L));
    }

    @Test
    void testGetProductSkus_withMatchingSize() {
        Product product = new Product();
        product.setId(1L);
        ProductAttribute attr = new ProductAttribute("SKU-1-1", 1L, 100.0, "M", "img.jpg");
        product.setAttributes(List.of(attr));

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        List<String> skus = productService.getProductSkus(1L, "M");

        assertEquals(1, skus.size());
        assertEquals("SKU-1-1", skus.get(0));
    }

    @Test
    void testGetProductSizes_success() {
        Product product = new Product();
        product.setId(1L);
        product.setAttributes(List.of(
                new ProductAttribute("SKU-1", 1L, 200.0, "M", "image1.jpg"),
                new ProductAttribute("SKU-2", 1L, 300.0, "L", "image2.jpg")
        ));

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        List<String> sizes = productService.getProductSizes(1L);
        assertTrue(sizes.contains("M"));
        assertTrue(sizes.contains("L"));
    }

    @Test
    void testImportProducts_success() {
        MultipartFile file = mock(MultipartFile.class);
        Product product = new Product();
        product.setName("Imported Product");
        product.setSellerId(5L);
        product.setCategoryName("Fashion");

        when(csvParserService.parseCsv(file)).thenReturn(List.of(product));

        CategoryDto category = new CategoryDto(20L, "Fashion", "fashion.jpg", "Fashion Category");
        when(categoryServiceClient.getCategoryByName("Fashion")).thenReturn(category);

        productService.importProducts(file);

        verify(productRepository).saveAll(anyList());
    }

    @Test
    void testImportProducts_missingSeller() {
        MultipartFile file = mock(MultipartFile.class);
        Product product = new Product();
        product.setName("Imported Product");
        product.setCategoryName("Fashion"); // no seller ID

        when(csvParserService.parseCsv(file)).thenReturn(List.of(product));

        CategoryDto category = new CategoryDto(20L, "Fashion", "fashion.jpg", "Fashion Category");
        when(categoryServiceClient.getCategoryByName("Fashion")).thenReturn(category);

        assertThrows(IllegalArgumentException.class, () -> productService.importProducts(file));
    }

    @Test
    void testGetProductSellerDetails_success() {
        Product product = new Product();
        product.setId(1L);
        product.setSellerId(100L);

        Seller seller = new Seller();
        seller.setId(100L);
        seller.setSellerName("ABC Pvt Ltd");
        seller.setContactName("John Doe");
        seller.setEmail("john@example.com");
        seller.setPhoneNumber("1234567890");
        seller.setAddressLine1("Street 1");
        seller.setAddressLine2("Street 2");
        seller.setCity("City");
        seller.setState("State");
        seller.setZipCode("000000");
        seller.setCountry("Country");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(sellerRepository.findById(100L)).thenReturn(Optional.of(seller));

        SellerDto sellerDto = productService.getProductSellerDetails(1L);

        assertEquals("ABC Pvt Ltd", sellerDto.getSellerName());
        assertEquals("john@example.com", sellerDto.getEmail());
    }
}
