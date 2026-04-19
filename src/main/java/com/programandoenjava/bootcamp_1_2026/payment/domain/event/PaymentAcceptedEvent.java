package com.programandoenjava.bootcamp_1_2026.payment.domain.event;

public record PaymentAcceptedEvent(
        Double amount,
        String customerEmail,
        String paymentProvider
) {
}
