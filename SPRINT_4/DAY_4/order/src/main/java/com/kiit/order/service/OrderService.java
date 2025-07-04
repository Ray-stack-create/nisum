package com.kiit.order.service;

import com.kiit.order.external.PaymentGatewayClient;
import com.kiit.order.model.Order;
import com.kiit.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentGatewayClient paymentGatewayClient;

    public OrderService(OrderRepository orderRepository, PaymentGatewayClient paymentGatewayClient) {
        this.orderRepository = orderRepository;
        this.paymentGatewayClient = paymentGatewayClient;
    }

    public Order createOrder(Order order) {
        paymentGatewayClient.charge(order.getProduct(), order.getAmount());
        return orderRepository.save(order);
    }
}

