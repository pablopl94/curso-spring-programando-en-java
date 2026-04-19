package com.programandoenjava.bootcamp_1_2026.order.infraestructure.api.dto.request;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestOrderFilter {

    @PastOrPresent(message = "createdAtFrom no puede ser futuro")
    private LocalDateTime createdAtFrom;

    @PastOrPresent(message = "createdAtTo no puede ser futuro")
    private LocalDateTime createdAtTo;

    @PositiveOrZero(message = "totalAmountMin no puede ser negativo")
    private Double totalAmountMin;

    @PositiveOrZero(message = "totalAmountMax no puede ser negativo")
    private Double totalAmountMax;

    @Size(max = 200, message = "productName no puede superar 200 caracteres")
    private String productName;
}
