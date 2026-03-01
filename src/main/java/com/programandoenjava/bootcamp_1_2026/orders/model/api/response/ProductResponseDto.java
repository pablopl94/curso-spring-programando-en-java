package com.programandoenjava.bootcamp_1_2026.orders.model.api.response;

public record ProductResponseDto(
        Long id,
        String name,
        Double price
) { }
