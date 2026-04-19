package com.programandoenjava.bootcamp_1_2026.order.infraestructure.api.dto.response;

public record OrderDashboardResponseDto(
        Long id,
        String customerName,
        Long totalProducts
) implements OrderViewResponseDto {
}