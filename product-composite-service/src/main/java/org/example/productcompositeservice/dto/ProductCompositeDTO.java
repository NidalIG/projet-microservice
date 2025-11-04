package org.example.productcompositeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductCompositeDTO {
    private ProductDTO product;
    private List<ReviewDTO> reviews;
    private List<RecommendationDTO> recommendations;

}

