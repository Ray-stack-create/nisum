package com.kiit.shipping.model;


import lombok.Data;

@Data
public class Order {
    private String orderId;
    private String product;
    private int quantity;

}

