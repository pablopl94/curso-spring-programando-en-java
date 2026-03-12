package com.programandoenjava.bootcamp_1_2026.orderItem.model.api;

public record OrderItemRequestDto(
        Integer quantity,
        Long idProduct
) { }