package com.programandoenjava.bootcamp_1_2026.orders.model.api.response;

import java.time.LocalDate;
import java.util.Set;

public record OrderResponseDto(
        Long id,
        Double totalAmount,
        String processorName,
        String customerName,
        String customerEmail,
        LocalDate createdAt,
        Set<OrderItemResponseDto> items
) { }
