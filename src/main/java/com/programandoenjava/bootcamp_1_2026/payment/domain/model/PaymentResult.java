package com.programandoenjava.bootcamp_1_2026.payment.domain.model;

import java.time.LocalDateTime;

public record PaymentResult(
        String transactionId,
        PaymentStatus status,
        LocalDateTime processedAt,
        Double amount
) {

    public PaymentResult {
        if (status == null)
            throw new IllegalArgumentException("El status es obligatorio");
        if (processedAt == null)
            throw new IllegalArgumentException("La fecha de procesamiento es obligatoria");
        if (amount == null || amount < 0)
            throw new IllegalArgumentException("El monto no puede ser negativo");
    }
}
