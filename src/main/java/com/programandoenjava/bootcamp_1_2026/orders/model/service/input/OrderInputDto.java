package com.programandoenjava.bootcamp_1_2026.orders.model.service.input;

import java.util.Set;

public record OrderInputDto(
        String customerName,
        String customerEmail,
        Set<OrderItemInputDto> items
) { }
