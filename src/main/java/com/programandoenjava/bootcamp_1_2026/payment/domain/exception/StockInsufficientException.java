package com.programandoenjava.bootcamp_1_2026.payment.domain.exception;

public class StockInsufficientException extends RuntimeException {
    public StockInsufficientException(Long productId) {
        super("Stock insuficiente para el producto con id: " + productId);
    }
}
