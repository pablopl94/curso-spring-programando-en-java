package com.programandoenjava.bootcamp_1_2026.product.model.api;

public record ProductResponseDto(
        Long id,
        String name,
        Double price,
        Integer stock
) { }
