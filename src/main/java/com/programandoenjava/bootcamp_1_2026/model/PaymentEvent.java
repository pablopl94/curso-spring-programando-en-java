package com.programandoenjava.bootcamp_1_2026.model;

public record PaymentEvent(
        double monto,
        String userEmail,
        String supplierEmail
) { }
