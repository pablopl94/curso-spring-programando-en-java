package com.programandoenjava.bootcamp_1_2026.orders.model.api.response;

public record OrderItemResponseDto(
        Long id,
        Integer quantity,
        Double unitPrice,
        ProductResponseDto product
) { }
