package com.programandoenjava.bootcamp_1_2026.order.domain.exception;

public class OrderAlreadyExistsException extends RuntimeException {
    public OrderAlreadyExistsException(Long id) {
        super("El pedido con id " + id + " ya existe.");
    }
}