package com.example.authorizationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-composite-service")
public interface ProductCompositeClient {

    @GetMapping("/api/composite/validate-role")
    boolean validateRole(@RequestParam("username") String username,
                         @RequestParam("role") String role);
}