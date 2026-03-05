package com.programandoenjava.bootcamp_1_2026.orders.model.service.output;

import com.programandoenjava.bootcamp_1_2026.orders.model.entity.OrderItem;

import java.time.LocalDateTime;
import java.util.Set;

public record OrderOutputDto(
        Long id,
        Double totalAmount,
        String processorName,
        String customerName,
        String customerEmail,
        LocalDateTime createdAt,
        Set<OrderItem> items
) { }
