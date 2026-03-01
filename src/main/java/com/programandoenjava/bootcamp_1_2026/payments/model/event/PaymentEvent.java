package com.programandoenjava.bootcamp_1_2026.payments.model.event;

public record PaymentEvent(
        double monto,
        String userEmail,
        String supplierEmail
) { }
