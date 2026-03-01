package com.programandoenjava.bootcamp_1_2026.orders.model.service.output;

public record OrderViewOutputDto(
        Long id,
        String customerName,
        Integer totalProducts
) {
}
