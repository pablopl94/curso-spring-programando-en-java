package com.programandoenjava.bootcamp_1_2026.orderItem.domain.exception;

public class OrderItemNotFoundException extends RuntimeException {
    public OrderItemNotFoundException(Long orderItemId) {
        super("Orden no encontrada con id: " + orderItemId);
    }
}