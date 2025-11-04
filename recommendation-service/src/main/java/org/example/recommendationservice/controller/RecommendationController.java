package org.example.recommendationservice.controller;

import jakarta.validation.Valid;
import org.example.recommendationservice.models.Recommendation;
import org.example.recommendationservice.service.RecommendationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationService service;

    public RecommendationController(RecommendationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Recommendation> createRecommendation(@Valid @RequestBody Recommendation recommendation) {
        Recommendation saved = service.saveRecommendation(recommendation);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Recommendation>> getByProduct(@PathVariable Integer productId) {
        List<Recommendation> list = service.getRecommendationsByProductId(productId);
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecommendation(@PathVariable Integer id) {
        service.deleteRecommendation(id);
        return ResponseEntity.noContent().build();
    }
}
