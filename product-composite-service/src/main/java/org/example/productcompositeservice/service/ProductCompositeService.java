package org.example.productcompositeservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.example.productcompositeservice.clients.ProductServiceClient;
import org.example.productcompositeservice.clients.RecommendationServiceClient;
import org.example.productcompositeservice.clients.ReviewServiceClient;
import org.example.productcompositeservice.dto.ProductCompositeDTO;
import org.example.productcompositeservice.dto.ProductDTO;
import org.example.productcompositeservice.dto.RecommendationDTO;
import org.example.productcompositeservice.dto.ReviewDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class ProductCompositeService {

    private final ProductServiceClient productClient;
    private final ReviewServiceClient reviewClient;
    private final RecommendationServiceClient recommendationClient;

    public ProductCompositeService(ProductServiceClient productClient,
                                   ReviewServiceClient reviewClient,
                                   RecommendationServiceClient recommendationClient) {
        this.productClient = productClient;
        this.reviewClient = reviewClient;
        this.recommendationClient = recommendationClient;
    }

    @CircuitBreaker(name = "productComposite", fallbackMethod = "getProductAggregateFallback")
    public ProductCompositeDTO getProductAggregate(Long productId) {
        log.info("Appel de getProductAggregate pour productId={}", productId);

        ProductDTO product = productClient.getProduct(productId);
        List<ReviewDTO> reviews = reviewClient.getReviewsByProduct(productId);
        List<RecommendationDTO> recommendations = recommendationClient.getRecommendationsByProduct(productId);

        return new ProductCompositeDTO(product, reviews, recommendations);
    }

    public ProductCompositeDTO getProductAggregateFallback(Long productId, Exception e) {
        log.warn("Fallback activé pour productId={} : {}", productId, e.getMessage());
        return new ProductCompositeDTO(null, new ArrayList<>(), new ArrayList<>());
    }
}
