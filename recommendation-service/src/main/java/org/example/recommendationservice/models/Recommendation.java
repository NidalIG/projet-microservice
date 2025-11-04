package org.example.recommendationservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recommendationId;

    @NotNull(message = "ProductId est obligatoire")
    private Integer productId;

    @NotBlank(message = "Author est obligatoire")
    private String author;

    @NotBlank(message = "Content est obligatoire")
    private String content;

    @Min(value = 0, message = "Rate doit être >= 0%")
    @Max(value = 100, message = "Rate doit être <= 100%")
    private Integer rate;  // Pourcentage entre 0 et 100
}
