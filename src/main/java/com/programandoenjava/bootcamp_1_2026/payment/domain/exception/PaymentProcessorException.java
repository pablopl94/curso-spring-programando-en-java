package com.programandoenjava.bootcamp_1_2026.payment.domain.exception;

public class PaymentProcessorException extends RuntimeException {

    private final String code;

    public PaymentProcessorException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
