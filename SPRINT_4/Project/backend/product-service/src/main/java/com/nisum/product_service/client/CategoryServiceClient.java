package com.nisum.product_service.client;

import com.nisum.product_service.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Component
public class CategoryServiceClient {

    private final RestTemplate restTemplate;

    @Value("${category.service.url:http://localhost:8082}")
    private String categoryServiceUrl;

    @Autowired
    public CategoryServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CategoryDto getCategoryById(Long id) {
        try {
            String url = categoryServiceUrl + "/categories/" + id;
            ResponseEntity<CategoryDto> response = restTemplate.getForEntity(url, CategoryDto.class);
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch category with id: " + id, e);
        }
    }

    public CategoryDto getCategoryByName(String name) {
        try {
            String url = categoryServiceUrl + "/categories/search/?name=" + name;
            ResponseEntity<CategoryDto> response = restTemplate.getForEntity(url, CategoryDto.class);
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch category with name: " + name, e);
        }
    }

    public List<CategoryDto> getAllCategories() {
        try {
            String url = categoryServiceUrl + "/categories";
            ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<CategoryDto>>() {}
            );
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch categories", e);
        }
    }
}