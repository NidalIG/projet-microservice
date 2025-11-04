package org.example.productcompositeservice.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationDTO {
    private Long recommendationId;
    private Long productId;
    private String author;
    private String content;
    private int rate; // pourcentage
 }
