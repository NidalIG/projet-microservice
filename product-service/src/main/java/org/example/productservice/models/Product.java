package org.example.productservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Le nom du produit est obligatoire")
    private String name;

    private String description;

    @Min(value = 0, message = "Le poids ne peut pas être négatif")
    @Max(value = 100, message = "Le poids ne peut pas dépasser 100 kg")
    private double weight;
}
