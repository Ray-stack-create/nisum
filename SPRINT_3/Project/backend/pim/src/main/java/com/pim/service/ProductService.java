package com.pim.service;

import com.pim.dto.ProductRequest;
import com.pim.dto.ProductResponse;
import com.pim.model.Product;
import com.pim.model.ProductAttribute;

import java.util.List;

public interface ProductService {
    String addCombinedProduct(ProductRequest productRequest);
    List<Product> getAllProducts();
    ProductResponse getProductById(Integer id);
    List<Product> searchProducts(String keyword);
    String updateProduct(Integer id, ProductRequest request);
    void deleteProduct(Integer id);
    String addAttributes(Integer productId, List<ProductAttribute> attributes);
    List<ProductAttribute> getAttributes(Integer productId);

}
