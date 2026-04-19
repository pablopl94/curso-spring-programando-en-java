package com.programandoenjava.bootcamp_1_2026.payment.domain.model;

public record PaymentItem(Long productId, Integer quantity, Double unitPrice) {

    public PaymentItem {
        if (productId == null)
            throw new IllegalArgumentException("El productId es obligatorio");
        if (quantity == null || quantity <= 0)
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
        if (unitPrice == null || unitPrice < 0)
            throw new IllegalArgumentException("El precio unitario no puede ser negativo");
    }
}
