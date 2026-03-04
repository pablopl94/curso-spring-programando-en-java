package com.programandoenjava.bootcamp_1_2026.exceptions;

public class PaymentException extends RuntimeException {
    public PaymentException(String message) {
        super("Error en el pago: " + message);
    }
}