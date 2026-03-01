package com.programandoenjava.bootcamp_1_2026.orders.model.service.output;

public record OrderItemOutputDto(
        Long id,
        Integer quantity,
        Double unitPrice,
        ProductOutputDto product
) { }
