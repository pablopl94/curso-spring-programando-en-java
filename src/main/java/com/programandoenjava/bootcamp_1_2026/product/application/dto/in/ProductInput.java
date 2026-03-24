package com.programandoenjava.bootcamp_1_2026.product.application.dto.in;

public record ProductInput(
        String name,
        Double price,
        Integer stock
) {
}
