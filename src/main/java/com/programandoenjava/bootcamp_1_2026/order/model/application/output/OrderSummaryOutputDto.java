package com.programandoenjava.bootcamp_1_2026.order.model.application.output;

import java.time.LocalDateTime;

public record OrderSummaryOutputDto(
        Long id,
        Double totalAmount,
        LocalDateTime createdAt,
        String processor
) { }
