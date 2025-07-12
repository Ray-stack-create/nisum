package com.example.service_a.service;

import com.example.service_a.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientService {

    @Autowired
    private JwtUtil jwtUtil;

    public ResponseEntity<String> callServiceB() {
        String token = jwtUtil.generateToken("service-a");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        return new RestTemplate().exchange(
                "http://localhost:8081/secure-endpoint",
                HttpMethod.GET,
                entity,
                String.class
        );
    }
}

