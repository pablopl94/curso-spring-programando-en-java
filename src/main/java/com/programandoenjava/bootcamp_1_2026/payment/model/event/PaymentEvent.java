package com.programandoenjava.bootcamp_1_2026.payment.model.event;

public record PaymentEvent(
        double monto,
        String userEmail,
        String supplierEmail
) { }
