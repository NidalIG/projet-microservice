package com.example.apiclient.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.apiclient.model.Product;

@Service
public class ProductServiceClient {

    private final RestTemplate restTemplate;

    @Value("${gateway.url}")
    private String gatewayUrl;

    public ProductServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getProductById(Long id) {
        String url = gatewayUrl + "/products/" + id; // via Gateway
        return restTemplate.getForObject(url, Product.class);
    }
}
