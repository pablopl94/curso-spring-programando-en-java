package com.programandoenjava.bootcamp_1_2026.payment.infrastructure.api.dto.in;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Set;

public record PaymentRequestDto(

        @NotNull(message = "El monto total es obligatorio")
        @Positive(message = "El monto total debe ser mayor que cero")
        Double totalAmount,

        @NotEmpty(message = "Debe incluir al menos un item")
        @Valid
        Set<PaymentItemRequestDto> items
) {
}
