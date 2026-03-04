package com.programandoenjava.bootcamp_1_2026.payments.service;

import com.programandoenjava.bootcamp_1_2026.exceptions.PaymentException;
import com.programandoenjava.bootcamp_1_2026.payments.model.api.request.PaymentRequest;
import com.programandoenjava.bootcamp_1_2026.payments.model.api.response.PaymentResponse;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentProcessor paymentProcessor;

    public PaymentService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public PaymentResponse processPayment(PaymentRequest request) {
        try {
            return paymentProcessor.process(request);
        } catch (Exception e) {
            throw new PaymentException("Fallo en el procesamiento");
        }
    }
}