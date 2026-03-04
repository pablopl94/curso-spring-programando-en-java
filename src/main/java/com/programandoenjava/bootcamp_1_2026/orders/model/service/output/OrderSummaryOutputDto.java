package com.programandoenjava.bootcamp_1_2026.orders.model.service.output;

import java.time.LocalDateTime;

public record OrderSummaryOutputDto(
        Long id,
        Double totalAmount,
        LocalDateTime createdAt,
        String processor
) { }
