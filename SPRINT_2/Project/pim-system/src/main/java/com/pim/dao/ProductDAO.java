package com.pim.dao;
import com.pim.model.Product;
import com.pim.model.ProductAttribute;
import java.util.List;


    public interface ProductDAO {

        // Product CRUD
        void addProduct(Product product);
        void updateProduct(Product product);
        void deleteProductById(int productId);
        Product getProductById(int productId);
        List<Product> getAllProducts();

        // Product Attribute operations
        void addProductAttributes(List<ProductAttribute> attributes);

        List<ProductAttribute> getAttributesByProductId(int productId);
    }
