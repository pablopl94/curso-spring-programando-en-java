package com.programandoenjava.bootcamp_1_2026.payment.service;

import com.programandoenjava.bootcamp_1_2026.payment.exception.PaymentException;
import com.programandoenjava.bootcamp_1_2026.payment.model.api.PaymentRequestDto;
import com.programandoenjava.bootcamp_1_2026.payment.model.api.PaymentResponseDto;
import com.programandoenjava.bootcamp_1_2026.payment.processor.PaymentProcessor;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentProcessor paymentProcessor;

    public PaymentService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public PaymentResponseDto processPayment(PaymentRequestDto request) {
        try {
            return paymentProcessor.process(request);
        } catch (Exception e) {
            throw new PaymentException("Fallo en el procesamiento");
        }
    }
}