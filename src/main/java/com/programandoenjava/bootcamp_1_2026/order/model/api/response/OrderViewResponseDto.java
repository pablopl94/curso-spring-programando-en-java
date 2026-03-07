package com.programandoenjava.bootcamp_1_2026.order.model.api.response;

public record OrderViewResponseDto(
        Long id,
        String customerName,
        Integer totalProducts
) { }
