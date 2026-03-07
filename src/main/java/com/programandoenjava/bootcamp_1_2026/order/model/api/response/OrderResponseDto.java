package com.programandoenjava.bootcamp_1_2026.order.model.api.response;

import com.programandoenjava.bootcamp_1_2026.orderItem.model.api.OrderItemResponseDto;

import java.time.LocalDateTime;
import java.util.Set;

public record OrderResponseDto(
        Long id,
        Double totalAmount,
        String processorName,
        String customerName,
        String customerEmail,
        LocalDateTime createdAt,
        Set<OrderItemResponseDto> items
) { }
