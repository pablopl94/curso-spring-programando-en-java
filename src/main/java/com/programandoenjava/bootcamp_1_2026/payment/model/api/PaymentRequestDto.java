package com.programandoenjava.bootcamp_1_2026.payment.model.api;

public record PaymentRequestDto(
        Double totalAmount,
        String userEmail,
        String provider
) {}