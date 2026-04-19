package com.programandoenjava.bootcamp_1_2026.payment.domain.model;

import java.util.Set;

public record PaymentCommand(
        String customerEmail,
        Double totalAmount,
        Set<PaymentItem> items
) {

    public PaymentCommand {
        if (customerEmail == null || customerEmail.isBlank())
            throw new IllegalArgumentException("El email del cliente es obligatorio");
        if (totalAmount == null || totalAmount <= 0)
            throw new IllegalArgumentException("El monto total debe ser mayor que cero");
        if (items == null || items.isEmpty())
            throw new IllegalArgumentException("El pago debe tener al menos un item");
    }
}
