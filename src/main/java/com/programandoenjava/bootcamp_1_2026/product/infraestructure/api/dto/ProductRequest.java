package com.programandoenjava.bootcamp_1_2026.product.infraestructure.api.dto;

public record ProductRequest(
        String name,
        Double price,
        Integer stock
) {
}