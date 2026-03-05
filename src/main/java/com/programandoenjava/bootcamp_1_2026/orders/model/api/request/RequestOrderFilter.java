package com.programandoenjava.bootcamp_1_2026.orders.model.api.request;

import java.time.LocalDateTime;

public record RequestOrderFilter(
        LocalDateTime createdAtFrom,
        LocalDateTime createdAtTo,
        Double totalAmountMin,
        Double totalAmountMax,
        String productName
) { }