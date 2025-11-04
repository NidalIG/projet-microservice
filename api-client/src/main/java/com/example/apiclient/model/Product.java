package com.example.apiclient.model;

import lombok.Data;

@Data
public class Product {
    private Long productId;
    private String name;
    private int weight;
}
