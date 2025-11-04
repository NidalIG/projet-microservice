package org.example.productcompositeservice.clients;


import org.example.productcompositeservice.dto.ReviewDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "review-service")
public interface ReviewServiceClient {

    @GetMapping("/reviews/product/{productId}")
    List<ReviewDTO> getReviewsByProduct(@PathVariable("productId") Long productId);
}

