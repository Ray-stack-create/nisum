package com.nisum.productdashboard.model;
import jakarta.validation.constraints.*;

public class Product {
    private int id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @PositiveOrZero(message = "Price must be non-negative")
    private double price;

    @Min(value = 0, message = "Stock quantity must be non-negative")
    private int stockQuantity;

    @NotBlank(message = "Category is required")
    private String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotBlank(message = "Name is required") String getName() {
        return name;
    }

    public @NotBlank(message = "Category is required") String getCategory() {
        return category;
    }

    public void setCategory(@NotBlank(message = "Category is required") String category) {
        this.category = category;
    }

    public @Min(value = 0, message = "Stock quantity must be non-negative") int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(@Min(value = 0, message = "Stock quantity must be non-negative") int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public @PositiveOrZero(message = "Price must be non-negative") double getPrice() {
        return price;
    }

    public void setPrice(@PositiveOrZero(message = "Price must be non-negative") double price) {
        this.price = price;
    }

    public @NotBlank(message = "Description is required") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "Description is required") String description) {
        this.description = description;
    }

    public void setName(@NotBlank(message = "Name is required") String name) {
        this.name = name;
    }
// Getters and Setters
}
