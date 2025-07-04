package com.kiit.order.repository;


import com.kiit.order.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void testSave() {
        Order order = new Order(null, "Pen", 49.0);
        Order saved = orderRepository.save(order);
        assertNotNull(saved.getId());
    }
}

