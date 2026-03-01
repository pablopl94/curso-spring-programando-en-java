package com.programandoenjava.bootcamp_1_2026.orders.model.api.response;

import java.time.LocalDate;
import java.util.Set;

public record OrderViewResponseDto(
        Long id,
        String customerName,
        Integer totalProducts
) { }
