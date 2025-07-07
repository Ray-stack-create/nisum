package com.pim.dto;
import com.pim.model.ProductAttribute;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;


@Data
public class ProductResponse {
    private Integer productId;
    private String productName;
    private String sku;
    private String status;
    private LocalDate createdDate;
    private LocalDate lastModifiedDate;
    private List<ProductAttribute> attributes;
    // Getters & Setters
}

