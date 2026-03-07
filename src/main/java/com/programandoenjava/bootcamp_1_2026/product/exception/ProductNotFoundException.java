package com.programandoenjava.bootcamp_1_2026.product.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long productId) {
        super("Producto no encontrado con id: " + productId);
    }
}