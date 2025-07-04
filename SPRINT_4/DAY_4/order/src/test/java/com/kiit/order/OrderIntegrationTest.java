package com.kiit.order;

import com.kiit.order.external.PaymentGatewayClient;
import com.kiit.order.model.Order;
import com.kiit.order.repository.OrderRepository;
import com.kiit.order.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.ExpectedCount.times;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(OrderIntegrationTest.MockClientConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentGatewayClient paymentGatewayClient;

    @BeforeEach
    void setup() {
        orderRepository.deleteAll();
        Mockito.reset(paymentGatewayClient);
    }

    @Test
    void testCreateOrder_callsGatewayOnce_andUsesRealBeans() {
        Order request = new Order(null, "Book", 299.99);

        ResponseEntity<Order> response = restTemplate.postForEntity("/orders", request, Order.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().getId());
        assertEquals("Book", response.getBody().getProduct());

        Mockito.verify(paymentGatewayClient).charge("Book", 299.99);
        assertTrue(orderRepository.count() > 0);
        assertNotNull(orderService);
    }

    @TestConfiguration
    static class MockClientConfig {
        @Bean
        public PaymentGatewayClient paymentGatewayClient() {
            return Mockito.mock(PaymentGatewayClient.class);
        }
    }
}
