package com.nisum.productval.model;
import jakarta.validation.constraints.*;
import com.nisum.productval.validation.ValidPrice;

public class Product {
    private int id;

    @NotBlank(message = "Product name must not be empty")
    @Size(min = 2, max = 50, message = "Product name must be between 2 and 50 characters")
    private String name;

    @ValidPrice
    private double price;

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotBlank(message = "Product name must not be empty") @Size(min = 2, max = 50, message = "Product name must be between 2 and 50 characters") String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(@NotBlank(message = "Product name must not be empty") @Size(min = 2, max = 50, message = "Product name must be between 2 and 50 characters") String name) {
        this.name = name;
    }
}