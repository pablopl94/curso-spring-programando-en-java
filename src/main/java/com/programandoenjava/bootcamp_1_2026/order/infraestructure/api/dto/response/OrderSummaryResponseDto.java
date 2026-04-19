package com.programandoenjava.bootcamp_1_2026.order.infraestructure.api.dto.response;


import java.time.LocalDateTime;

public record OrderSummaryResponseDto(
        Long id,
        Double totalAmount,
        String processorName,
        LocalDateTime createdAt
) implements OrderViewResponseDto {
}
