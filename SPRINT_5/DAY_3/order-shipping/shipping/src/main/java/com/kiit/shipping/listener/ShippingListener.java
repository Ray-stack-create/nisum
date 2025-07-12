package com.kiit.shipping.listener;

import com.kiit.shipping.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ShippingListener {

    @KafkaListener(topics = "order.created", groupId = "shipping-group")
    public void handleOrder(Order order) {
        System.out.println("Received order: " + order.getOrderId());
        System.out.println("Reserving stock for product: " + order.getProduct());
        System.out.println("Shipping confirmed for order: " + order.getOrderId());
    }
}
