package com.programandoenjava.bootcamp_1_2026.payments.model.api.response;

import com.programandoenjava.bootcamp_1_2026.payments.model.constants.StatusPaymentEnum;

import java.time.LocalDateTime;

public record PaymentResponse(
        String transactionId,
        StatusPaymentEnum status,
        LocalDateTime processedAt,
        double amount
) {}