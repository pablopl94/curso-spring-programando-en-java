package com.programandoenjava.bootcamp_1_2026.orders.model.api.request;

import java.util.Set;

public record OrderRequestDto(
        String customerName,
        String customerEmail,
        Set<OrderItemRequestDto> items
) {}