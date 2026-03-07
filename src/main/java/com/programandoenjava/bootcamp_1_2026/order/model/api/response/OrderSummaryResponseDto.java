package com.programandoenjava.bootcamp_1_2026.order.model.api.response;

import java.time.LocalDateTime;

public record OrderSummaryResponseDto(
        Long id,
        Double totalAmount,
        LocalDateTime createdAt,
        String processor
) { }
