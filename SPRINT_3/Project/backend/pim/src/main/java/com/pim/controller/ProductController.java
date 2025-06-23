package com.pim.controller;
import com.pim.dto.ProductRequest;
import com.pim.dto.ProductResponse;
import com.pim.model.Product;
import com.pim.model.ProductAttribute;
import com.pim.repository.ProductAttributeRepository;
import com.pim.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private ProductAttributeRepository attributeRepo;

    @PostMapping
    public ResponseEntity<String> addCombinedProduct(@RequestBody Map<String, String> payload) {
        try {
            Product product = new Product();
            product.setProductName(payload.get("productName"));
            product.setSku(payload.get("sku"));
            product.setCategory(payload.get("category"));
            product.setStatus(payload.get("status"));

            // Parse createdDate
            String dateStr = payload.get("createdDate");
            product.setCreatedDate(LocalDate.parse(dateStr));
            product.setLastModifiedDate(LocalDate.now());

            Product savedProduct = productRepo.save(product);

            List<ProductAttribute> attributes = new ArrayList<>();
            if (payload.get("color") != null && !payload.get("color").isEmpty())
                attributes.add(new ProductAttribute("Color", payload.get("color"), savedProduct));
            if (payload.get("size") != null && !payload.get("size").isEmpty())
                attributes.add(new ProductAttribute("Size", payload.get("size"), savedProduct));
            if (payload.get("material") != null && !payload.get("material").isEmpty())
                attributes.add(new ProductAttribute("Material", payload.get("material"), savedProduct));
            if (payload.get("pattern") != null && !payload.get("pattern").isEmpty())
                attributes.add(new ProductAttribute("Pattern", payload.get("pattern"), savedProduct));
            if (payload.get("season") != null && !payload.get("season").isEmpty())
                attributes.add(new ProductAttribute("Season", payload.get("season"), savedProduct));

            attributeRepo.saveAll(attributes);

            return ResponseEntity.ok("Product and attributes added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }





    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Integer id) {
        return productRepo.findById(id).map(product -> {
            List<ProductAttribute> attributes = attributeRepo.findByProduct_ProductId(product.getProductId());

            ProductResponse response = new ProductResponse();
            response.setProductId(product.getProductId());
            response.setProductName(product.getProductName());
            response.setSku(product.getSku());
            response.setStatus(product.getStatus());
            response.setCreatedDate(product.getCreatedDate());
            response.setLastModifiedDate(product.getLastModifiedDate());
            response.setAttributes(attributes);

            return ResponseEntity.ok(response);
        }).orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam("q") String keyword) {
        List<Product> result = productRepo.searchByKeyword(keyword);
        return ResponseEntity.ok(result);
    }





    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<String> updateProduct(@PathVariable Integer id, @RequestBody Map<String, String> payload) {
        return productRepo.findById(id).map(product -> {
            product.setProductName(payload.get("productName"));
            product.setCategory(payload.get("category"));
            product.setStatus(payload.get("status"));

            String createdDate = payload.get("createdDate");
            if (createdDate != null && !createdDate.isEmpty()) {
                product.setCreatedDate(LocalDate.parse(createdDate));
            }

            product.setLastModifiedDate(LocalDate.now());
            productRepo.save(product);

            // 🔥 This line requires a transaction
            attributeRepo.deleteByProduct_ProductId(id);

            List<ProductAttribute> updatedAttrs = new ArrayList<>();
            if (payload.get("color") != null)
                updatedAttrs.add(new ProductAttribute("Color", payload.get("color"), product));
            if (payload.get("size") != null)
                updatedAttrs.add(new ProductAttribute("Size", payload.get("size"), product));
            if (payload.get("material") != null)
                updatedAttrs.add(new ProductAttribute("Material", payload.get("material"), product));
            if (payload.get("pattern") != null)
                updatedAttrs.add(new ProductAttribute("Pattern", payload.get("pattern"), product));
            if (payload.get("season") != null)
                updatedAttrs.add(new ProductAttribute("Season", payload.get("season"), product));

            attributeRepo.saveAll(updatedAttrs);

            return ResponseEntity.ok("Product and attributes updated successfully.");
        }).orElse(ResponseEntity.notFound().build());
    }







    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/attributes")
    public ResponseEntity<String> addAttributes(@PathVariable Integer id, @RequestBody List<ProductAttribute> attributes) {
        Product product = productRepo.findById(id).orElseThrow();
        attributes.forEach(attr -> attr.setProduct(product));
        attributeRepo.saveAll(attributes);
        return ResponseEntity.ok("Attributes saved");
    }

    @GetMapping("/{id}/attributes")
    public ResponseEntity<List<ProductAttribute>> getAttributes(@PathVariable Integer id) {
        return ResponseEntity.ok(attributeRepo.findByProduct_ProductId(id));
    }
}








