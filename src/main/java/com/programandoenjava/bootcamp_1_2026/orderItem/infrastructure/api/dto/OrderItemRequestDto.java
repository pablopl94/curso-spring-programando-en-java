package com.programandoenjava.bootcamp_1_2026.orderItem.infrastructure.api.dto;

public record OrderItemRequestDto(
        Integer quantity,
        Long idProduct
) {
}