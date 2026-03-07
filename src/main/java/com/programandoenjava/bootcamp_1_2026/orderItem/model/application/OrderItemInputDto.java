package com.programandoenjava.bootcamp_1_2026.orderItem.model.application;

public record OrderItemInputDto(
        Integer quantity,
        Double unitPrice,
        Long idProduct
) { }
