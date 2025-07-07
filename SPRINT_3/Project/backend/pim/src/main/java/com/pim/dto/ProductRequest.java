package com.pim.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank(message = "Product name is required")
    private String productName;

    @NotBlank(message = "SKU is required")
    private String sku;

    @NotBlank(message = "Status is required")
    private String status;

    @NotBlank(message = "Category is required")
    private String category;


    private String color;
    private String size;
    private String material;
    private String pattern;
    private String season;

    private String createdDate; // Optional, parse in service
}
