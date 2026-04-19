package com.programandoenjava.bootcamp_1_2026.payment.domain.service;

import com.programandoenjava.bootcamp_1_2026.payment.domain.model.PaymentStatus;

public class PaymentStatusEvaluator {

    private static final double FRAUD_THRESHOLD = 50_000;
    private static final double VERIFICATION_THRESHOLD = 5_000;

    public PaymentStatus evaluate(double amount) {
        if (amount >= FRAUD_THRESHOLD) return PaymentStatus.DETECTED_FRAUD;
        if (amount >= VERIFICATION_THRESHOLD) return PaymentStatus.REQUIRES_VERIFICATION;
        return PaymentStatus.ACCEPTED;
    }
}
