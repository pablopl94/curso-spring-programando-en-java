package com.programandoenjava.bootcamp_1_2026.payment.infrastructure.api.dto.out;

import com.programandoenjava.bootcamp_1_2026.payment.domain.model.PaymentStatus;

import java.time.LocalDateTime;

public record PaymentResponseDto(
        String transactionId,
        PaymentStatus status,
        LocalDateTime processedAt,
        Double amount
) {
}
