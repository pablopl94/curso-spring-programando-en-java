package com.programandoenjava.bootcamp_1_2026.product.application.dto.out;

public record ProductOutput(
        Long id,
        String name,
        Double price,
        Integer stock
) {
}
