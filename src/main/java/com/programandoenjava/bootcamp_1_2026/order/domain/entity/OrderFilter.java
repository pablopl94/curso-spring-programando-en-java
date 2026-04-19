package com.programandoenjava.bootcamp_1_2026.order.domain.entity;

import java.time.LocalDateTime;

public record OrderFilter(
        LocalDateTime createdAtFrom,
        LocalDateTime createdAtTo,
        Double totalAmountMin,
        Double totalAmountMax,
        String productName
) {

    public boolean hasAnyField() {
        return createdAtFrom != null
                || createdAtTo != null
                || totalAmountMin != null
                || totalAmountMax != null
                || (productName != null && !productName.isBlank());
    }
}
