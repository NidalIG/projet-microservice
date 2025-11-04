package org.example.recommendationservice.service;

import org.example.recommendationservice.models.Recommendation;
import org.example.recommendationservice.repository.RecommendationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    private final RecommendationRepository repository;

    public RecommendationService(RecommendationRepository repository) {
        this.repository = repository;
    }

    public Recommendation saveRecommendation(Recommendation recommendation) {
        return repository.save(recommendation);
    }

    public List<Recommendation> getRecommendationsByProductId(Integer productId) {
        return repository.findByProductId(productId);
    }

    public void deleteRecommendation(Integer id) {
        repository.deleteById(id);
    }
}
