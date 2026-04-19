package com.programandoenjava.bootcamp_1_2026.order.infraestructure.api.dto.response;

import com.programandoenjava.bootcamp_1_2026.orderItem.infrastructure.api.dto.OrderItemResponseDto;

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

) implements OrderViewResponseDto {
}
