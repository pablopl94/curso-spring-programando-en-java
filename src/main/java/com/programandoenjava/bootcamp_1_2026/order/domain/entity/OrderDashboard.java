package com.programandoenjava.bootcamp_1_2026.order.domain.entity;

public record OrderDashboard(
        Long id,
        String customerName,
        Long totalProducts
) implements OrderView {
}