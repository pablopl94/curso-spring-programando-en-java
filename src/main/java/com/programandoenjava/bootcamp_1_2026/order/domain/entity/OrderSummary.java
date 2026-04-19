package com.programandoenjava.bootcamp_1_2026.order.domain.entity;


import java.time.LocalDateTime;

public record OrderSummary(
        Long id,
        Double totalAmount,
        String processorName,
        LocalDateTime createdAt
) implements OrderView {
}
