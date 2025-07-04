package com.example.product.service;
import com.example.product.model.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    private final RestTemplate restTemplate;

    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDTO getUserById(Long userId) {
        return restTemplate.getForObject("http://localhost:8081/users/" + userId, UserDTO.class);
    }
}
