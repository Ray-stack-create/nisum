package com.nisum.product_service.service;
import com.nisum.product_service.client.CategoryServiceClient;
import com.nisum.product_service.dto.CategoryDto;
import com.nisum.product_service.dto.PaginationResult;
import com.nisum.product_service.dto.ProductAttributeDto;
import com.nisum.product_service.dto.ProductDto;
import com.nisum.product_service.dto.SellerDto;
import com.nisum.product_service.exception.CategoryNotFoundException;
import com.nisum.product_service.exception.ProductNotFoundException;
import com.nisum.product_service.model.Product;
import com.nisum.product_service.model.ProductAttribute;
import com.nisum.product_service.model.Seller;
import com.nisum.product_service.repository.ProductRepository;
import com.nisum.product_service.repository.SellerRepository;
import com.nisum.product_service.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryServiceClient categoryServiceClient;
    private final CsvParserService csvParserService;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          CategoryServiceClient categoryServiceClient,
                          CsvParserService csvParserService) {
        this.productRepository = productRepository;
        this.categoryServiceClient = categoryServiceClient;
        this.csvParserService = csvParserService;
    }

    public int getTotalProductCount() {
        return (int) productRepository.count();
    }

    public PaginationResult<ProductDto> getAllProducts(
            int page,
            int size,
            String status,
            String search,
            String sortBy,
            Long categoryId,
            Double minPrice,
            Double maxPrice,
            String sort) {

        String sortField = (sortBy != null && !sortBy.isEmpty()) ? sortBy : "lastModifiedDate";
        Sort.Direction direction = "asc".equalsIgnoreCase(sort) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(direction, sortField));

        Specification<Product> spec = Specification.where(null);

        if (search != null && !search.trim().isEmpty()) {
            spec = spec.and(ProductSpecification.searchProducts(search));
        }
        if (status != null && !status.trim().isEmpty()) {
            spec = spec.and(ProductSpecification.hasStatus(status));
        }
        if (categoryId != null) {
            spec = spec.and(ProductSpecification.hasCategoryId(categoryId));
        }
        if (minPrice != null && maxPrice != null) {
            spec = spec.and(ProductSpecification.priceBetween(minPrice, maxPrice));
        }

        Page<Product> productPage = productRepository.findAll(spec, pageable);


        LocalDateTime threshold = LocalDateTime.now().minusDays(1);
        int recentlyUpdatedCount = productRepository.countRecentlyModifiedProducts(threshold);

        List<ProductDto> productDtos = productPage.getContent().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return new PaginationResult<>(
                productDtos,
                getTotalProductCount(),
                recentlyUpdatedCount,
                page,
                size,
                (int) productPage.getTotalElements()
        );
    }


    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        return convertToDto(product);
    }

    public ProductDto createProduct(ProductDto productDto) {
        validateProductDto(productDto);

        validateCategory(productDto.getCategoryId());

        Product product = new Product();
        product.setName(productDto.getName());
        product.setCategoryId(productDto.getCategoryId());
        product.setStatus(productDto.getStatus() != null ? productDto.getStatus() : "Active");
        product.setSellerId(productDto.getSellerId());
        product.setLastModifiedDate(LocalDateTime.now());

        // Save the product first to get the generated ID
        Product savedProduct = productRepository.save(product);

        // Now create and set attributes with auto-generated SKUs
        if (productDto.getAttributes() != null) {
            final Product finalProduct = savedProduct; // Make it effectively final for lambda
            List<ProductAttribute> attributes = productDto.getAttributes().stream()
                    .map(attr -> {
                        // Auto-generate SKU in format: SKU-productID-autoIncrementNumber
                        int attributeIndex = productDto.getAttributes().indexOf(attr) + 1;
                        String autoGeneratedSku = "SKU-" + finalProduct.getId() + "-" + attributeIndex;
                        
                        return new ProductAttribute(
                                autoGeneratedSku,
                                finalProduct.getId(),
                                attr.getPrice(),
                                attr.getSize(),
                                attr.getProductImage()
                        );
                    })
                    .collect(Collectors.toList());

            // Set the product reference for each attribute
            attributes.forEach(attr -> attr.setProduct(finalProduct));
            savedProduct.setAttributes(attributes);

            // Save again to persist the attributes
            savedProduct = productRepository.save(savedProduct);
        }

        return convertToDto(savedProduct);
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        validateProductDto(productDto);

        if (!existingProduct.getCategoryId().equals(productDto.getCategoryId())) {
            validateCategory(productDto.getCategoryId());
        }

        existingProduct.setName(productDto.getName());
        existingProduct.setCategoryId(productDto.getCategoryId());
        existingProduct.setStatus(productDto.getStatus());
        existingProduct.setSellerId(productDto.getSellerId());

        // Handle attributes update
        if (productDto.getAttributes() != null && !productDto.getAttributes().isEmpty()) {
            // Clear existing attributes
            existingProduct.getAttributes().clear();

            // Add new/updated attributes
            for (int i = 0; i < productDto.getAttributes().size(); i++) {
                ProductAttributeDto attrDto = productDto.getAttributes().get(i);
                
                ProductAttribute attribute = new ProductAttribute();
                
                // If SKU is provided (existing attribute), use it; otherwise generate new one
                if (attrDto.getSku() != null && !attrDto.getSku().trim().isEmpty()) {
                    attribute.setSku(attrDto.getSku().trim());
                } else {
                    // Generate new SKU for new attributes
                    attribute.setSku("SKU-" + id + "-" + (i + 1));
                }
                
                attribute.setPrice(attrDto.getPrice());
                attribute.setSize(attrDto.getSize().toUpperCase());
                attribute.setProductImage(attrDto.getProductImage());
//                attribute.setProduct(existingProduct);
                attribute.setProduct(existingProduct);                // sets the relationship
                attribute.setProductId(existingProduct.getId());      // sets the FK field (mandatory)

                existingProduct.getAttributes().add(attribute);
            }
        }

        Product updatedProduct = productRepository.save(existingProduct);
        return convertToDto(updatedProduct);
    }

    public boolean deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
        return true;
    }


    public void importProducts(MultipartFile file) {
        List<Product> products = csvParserService.parseCsv(file);

        for (Product product : products) {
            CategoryDto category = categoryServiceClient.getCategoryByName(product.getCategoryName());
            if (category == null) {
                throw new CategoryNotFoundException("Category not found: " + product.getCategoryName());
            }

            product.setCategoryId(category.getId());
            product.setStatus(product.getStatus().toUpperCase());



            // Ensure sellerId is set (if not, set a default or throw error)
            if (product.getSellerId() == null) {
                throw new IllegalArgumentException("Seller ID is required for import");
            }
        }

        productRepository.saveAll(products);
    }

    private ProductDto convertToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setCategoryId(product.getCategoryId());
        dto.setStatus(product.getStatus());
        dto.setSellerId(product.getSellerId());
        dto.setLastModifiedDate(product.getLastModifiedDate());
        try {
            CategoryDto category = categoryServiceClient.getCategoryById(product.getCategoryId());
            dto.setCategoryName(category.getName());
        } catch (Exception e) {
            dto.setCategoryName("Unknown");
        }
        // Map attributes
        if (product.getAttributes() != null) {
            List<ProductAttributeDto> attributeDtos = product.getAttributes().stream()
                .map(attr -> new ProductAttributeDto(
                    attr.getSku(),
                    attr.getPrice(),
                    attr.getSize(),
                    attr.getProductImage()
                ))
                .collect(Collectors.toList());
            dto.setAttributes(attributeDtos);
        }
        return dto;
    }

    private void validateProductDto(ProductDto productDto) {
        if (productDto.getName() == null || productDto.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name is required");
        }

        if (productDto.getCategoryId() == null) {
            throw new IllegalArgumentException("Category ID is required");
        }
        if (productDto.getSellerId() == null) {
            throw new IllegalArgumentException("Seller ID is required");
        }
    }

    private void validateCategory(Long categoryId) {
        CategoryDto category = categoryServiceClient.getCategoryById(categoryId);
        if (category == null) {
            throw new CategoryNotFoundException("Category not found with id: " + categoryId);
        }
    }

    public List<Long> getAllProductIds() {
        return productRepository.findAll().stream()
                .map(Product::getId)
                .collect(Collectors.toList());
    }

    public List<String> getProductSizes(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        // Assuming sizes are stored as a comma-separated string in the product entity
        String sizes = product.getAttributes().stream()
                .map(ProductAttribute::getSize)
                .filter(size -> size != null && !size.trim().isEmpty())
                .collect(Collectors.joining(","));

        if (sizes.isEmpty()) {
            throw new ProductNotFoundException("No sizes found for product with id: " + id);

        }
        return List.of(sizes.split(","));
    }

    public List<Long> getProductSellers(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        // If you want all sellers for the product's attributes (if each attribute can have a different seller)
        // Otherwise, if sellerId is only on Product, just return that
        // Here, we assume sellerId is only on Product
        return List.of(product.getSellerId());
    }

    public SellerDto getProductSellerDetails(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
        Long sellerId = product.getSellerId();
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found with id: " + sellerId));
        return new SellerDto(
                seller.getId(),
                seller.getSellerName(),
                seller.getContactName(),
                seller.getEmail(),
                seller.getPhoneNumber(),
                seller.getAddressLine1(),
                seller.getAddressLine2(),
                seller.getCity(),
                seller.getState(),
                seller.getZipCode(),
                seller.getCountry()
        );
    }

    public List<String> getProductSkus(Long id, String size) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        if (product.getAttributes() == null || product.getAttributes().isEmpty()) {
            return List.of();
        }

        return product.getAttributes().stream()
                .filter(attr -> size == null || size.trim().isEmpty() || size.equalsIgnoreCase(attr.getSize()))
                .map(ProductAttribute::getSku)
                .filter(sku -> sku != null && !sku.trim().isEmpty())
                .collect(Collectors.toList());
    }
}
