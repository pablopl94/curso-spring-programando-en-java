package com.programandoenjava.bootcamp_1_2026.orders.model.service.output;

import java.time.LocalDate;

public record OrderSummaryOutputDto(
        Long id,
        Double totalAmount,
        LocalDate createdAt,
        String processor
) { }
