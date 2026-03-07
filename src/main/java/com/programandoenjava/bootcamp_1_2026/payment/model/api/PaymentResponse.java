package com.programandoenjava.bootcamp_1_2026.payment.model.api;

import com.programandoenjava.bootcamp_1_2026.payment.model.constants.StatusPaymentEnum;

import java.time.LocalDateTime;

public record PaymentResponse(
        String transactionId,
        StatusPaymentEnum status,
        LocalDateTime processedAt,
        double amount
) {}