package com.pim.controller;

import com.pim.dao.ProductDAO;
import com.pim.model.AttributeWrapper;
import com.pim.model.Product;
import com.pim.model.ProductAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductDAO productDAO;

    //  Add product
    @PostMapping("/add")
    public void addProduct(@RequestParam String productName,
            @RequestParam String category,
            @RequestParam String status,
            @RequestParam String date,
            HttpServletResponse response) throws IOException {
        Product product = new Product();
        product.setProductName(productName);
        product.setCategory(category);
        product.setStatus(status);
        product.setCreatedDate(Timestamp.valueOf(date));

        productDAO.addProduct(product);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    //  Delete product
    @PostMapping("/delete")
    public void deleteProductById(@RequestParam int productId, HttpServletResponse response) {
        productDAO.deleteProductById(productId);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    //  Get all products
    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<?> getAllProductsJson() {
        List<Product> products = productDAO.getAllProducts();

        int totalCount = products.size();
        long recentCount = products.stream()
                .filter(p -> p.getCreatedDate().after(new Timestamp(System.currentTimeMillis() - 86400000))) // last 24
                                                                                                             // hours
                .count();

        return ResponseEntity.ok(
                Map.of(
                        "totalCount", totalCount,
                        "recentCount", recentCount,
                        "products", products));
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateProduct(@RequestParam int productId,
            @RequestParam String productName,
            @RequestParam String category,
            @RequestParam String status,
            @RequestParam String date) {
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setCategory(category);
        product.setStatus(status);
        product.setLastModified(Timestamp.valueOf(date));

        productDAO.updateProduct(product);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {
        Product product = productDAO.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/attribute-add-multiple")
    public ResponseEntity<String> addMultipleAttributes(@RequestBody AttributeWrapper request) {
        int productId = request.getProductId();
        List<ProductAttribute> attributes = new ArrayList<>();

        System.out.println("=== Incoming Attribute Submission ===");
        System.out.println("Product ID: " + productId);

        for (ProductAttribute attr : request.getAttributes()) {
            ProductAttribute newAttr = new ProductAttribute();
            newAttr.setProductId(productId);
            newAttr.setAttributeName(attr.getAttributeName());
            newAttr.setAttributeValue(attr.getAttributeValue());

            System.out.println("🟡 Inserting: " + newAttr);
            attributes.add(newAttr);
        }

        productDAO.addProductAttributes(attributes); 

        return ResponseEntity.status(HttpStatus.CREATED).body("Attributes added successfully.");
    }

    //  Get attributes for a product

    @GetMapping("/attribute/{productId}")
    @ResponseBody
    public List<ProductAttribute> getAttributesByProductId(@PathVariable int productId) {
        return productDAO.getAttributesByProductId(productId);
    }

}
