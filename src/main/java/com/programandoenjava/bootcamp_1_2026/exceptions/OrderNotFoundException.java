package com.programandoenjava.bootcamp_1_2026.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long orderId) {
        super("Orden no encontrada con id: " + orderId);
    }
}