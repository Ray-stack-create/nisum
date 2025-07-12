package com.kiit.order.controller;

import com.kiit.order.model.Order;
import com.kiit.order.service.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderProducer orderProducer;

    @PostMapping
    public String placeOrder(@RequestBody Order order) {
        orderProducer.sendOrder(order);
        return "Order placed: " + order.getOrderId();
    }
}
