package com.kiit.order.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private String product;
    private double amount;

    public Order(Long id, String product, double amount) {
        this.id = id;
        this.product = product;
        this.amount = amount;
    }

    // constructors, getters, setters
}
