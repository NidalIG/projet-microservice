package org.example.productcompositeservice.clients;


import org.example.productcompositeservice.dto.RecommendationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "recommendation-service")
public interface RecommendationServiceClient {

    @GetMapping("/recommendations/product/{productId}")
    List<RecommendationDTO> getRecommendationsByProduct(@PathVariable("productId") Long productId);
}

