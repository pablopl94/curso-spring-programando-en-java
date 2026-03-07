package com.programandoenjava.bootcamp_1_2026.order.model.application.output;

public record OrderViewOutputDto(
        Long id,
        String customerName,
        Integer totalProducts
) {
}
