package com.programandoenjava.bootcamp_1_2026.orders.model.api.response;

import java.time.LocalDate;

public record OrderSummaryResponseDto(
        Long id,
        Double totalAmount,
        LocalDate createdAt,
        String processor
) { }
