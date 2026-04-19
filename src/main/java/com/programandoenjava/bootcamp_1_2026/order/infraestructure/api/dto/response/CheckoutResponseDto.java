package com.programandoenjava.bootcamp_1_2026.order.infraestructure.api.dto.response;

import com.programandoenjava.bootcamp_1_2026.orderItem.infrastructure.api.dto.OrderItemResponseDto;

import java.util.Set;

public record CheckoutResponseDto(
        Double totalAmount,
        String emailCustomer,
        Set<OrderItemResponseDto> items
) {
}