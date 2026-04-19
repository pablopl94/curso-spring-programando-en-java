package com.programandoenjava.bootcamp_1_2026.payment.domain.exception;

public class PriceChangedException extends RuntimeException {
    public PriceChangedException(Long productId) {
        super("El precio del producto con id " + productId + " ha cambiado");
    }
}
