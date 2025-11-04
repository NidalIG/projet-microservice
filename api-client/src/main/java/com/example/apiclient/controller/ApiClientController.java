package com.example.apiclient.controller;

import com.example.apiclient.model.Product;
import com.example.apiclient.service.ProductServiceClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiClientController {

    private final ProductServiceClient productServiceClient;

    public ApiClientController(ProductServiceClient productServiceClient) {
        this.productServiceClient = productServiceClient;
    }

    @GetMapping("/test/products/{id}")
    public Product testGetProduct(@PathVariable Long id) {
        return productServiceClient.getProductById(id);
    }
}
