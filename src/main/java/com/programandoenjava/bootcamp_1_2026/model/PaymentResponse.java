package com.programandoenjava.bootcamp_1_2026.model;

import java.time.LocalDateTime;

public record PaymentResponse(
        String transactionId,
        StatusPaymentEnum status,
        LocalDateTime processedAt,
        double amount
) {}