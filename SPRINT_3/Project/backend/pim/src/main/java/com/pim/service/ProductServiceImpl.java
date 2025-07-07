package com.pim.service;

import com.pim.dto.ProductRequest;
import com.pim.dto.ProductResponse;
import com.pim.exception.ResourceNotFoundException;
import com.pim.model.Product;
import com.pim.model.ProductAttribute;
import com.pim.repository.ProductAttributeRepository;
import com.pim.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;
    private final ProductAttributeRepository attributeRepo;

    @Override
    public String addCombinedProduct(ProductRequest req) {
        Product product = new Product();
        product.setProductName(req.getProductName());
        product.setSku(req.getSku());
        product.setStatus(req.getStatus());
        product.setCategory(req.getCategory());
        product.setCreatedDate(LocalDate.now());
        product.setLastModifiedDate(LocalDate.now());

        Product savedProduct = productRepo.save(product);

        List<ProductAttribute> attributes = extractAttributes(req, savedProduct);
        attributeRepo.saveAll(attributes);

        return "Product and attributes added successfully.";
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public ProductResponse getProductById(Integer id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        List<ProductAttribute> attributes = attributeRepo.findByProduct_ProductId(product.getProductId());

        ProductResponse response = new ProductResponse();
        response.setProductId(product.getProductId());
        response.setProductName(product.getProductName());
        response.setSku(product.getSku());
        response.setStatus(product.getStatus());
        response.setCreatedDate(product.getCreatedDate());
        response.setLastModifiedDate(product.getLastModifiedDate());
        response.setAttributes(attributes);

        return response;
    }


    @Override
    public List<Product> searchProducts(String keyword) {
        return productRepo.searchByKeyword(keyword);
    }

    @Override
    @Transactional
    public String updateProduct(Integer id, ProductRequest req) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot update. Product not found with id: " + id));

        product.setProductName(req.getProductName());
        product.setCategory(req.getCategory());
        product.setStatus(req.getStatus());

        if (req.getCreatedDate() != null)
            product.setCreatedDate(LocalDate.parse(req.getCreatedDate()));

        product.setLastModifiedDate(LocalDate.now());
        productRepo.save(product);

        attributeRepo.deleteByProduct_ProductId(id);
        List<ProductAttribute> updatedAttrs = extractAttributes(req, product);
        attributeRepo.saveAll(updatedAttrs);

        return "Product and attributes updated successfully.";
    }


    @Override
    public void deleteProduct(Integer id) {
        if (!productRepo.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Product not found with id: " + id);
        }
        productRepo.deleteById(id);
    }


    @Override
    public String addAttributes(Integer productId, List<ProductAttribute> attributes) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot add attributes. Product not found with id: " + productId));

        attributes.forEach(attr -> attr.setProduct(product));
        attributeRepo.saveAll(attributes);
        return "Attributes saved";
    }


    @Override
    public List<ProductAttribute> getAttributes(Integer productId) {
        return attributeRepo.findByProduct_ProductId(productId);
    }

    private List<ProductAttribute> extractAttributes(ProductRequest req, Product product) {
        List<ProductAttribute> attributes = new ArrayList<>();
        if (req.getColor() != null) attributes.add(new ProductAttribute("Color", req.getColor(), product));
        if (req.getSize() != null) attributes.add(new ProductAttribute("Size", req.getSize(), product));
        if (req.getMaterial() != null) attributes.add(new ProductAttribute("Material", req.getMaterial(), product));
        if (req.getPattern() != null) attributes.add(new ProductAttribute("Pattern", req.getPattern(), product));
        if (req.getSeason() != null) attributes.add(new ProductAttribute("Season", req.getSeason(), product));
        return attributes;
    }
}
