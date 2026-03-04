package com.programandoenjava.bootcamp_1_2026.orders.model.service.input;

public record OrderItemInputDto(
        Integer quantity,
        Double unitPrice,
        Long idProduct
) { }
