package com.programandoenjava.bootcamp_1_2026.order.infraestructure.api.dto.request;

import com.programandoenjava.bootcamp_1_2026.orderItem.infrastructure.api.dto.OrderItemRequestDto;

import java.util.Set;

public record OrderRequestDto(
        Set<OrderItemRequestDto> items
) {
}