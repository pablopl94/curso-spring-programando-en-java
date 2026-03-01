package com.programandoenjava.bootcamp_1_2026.orders.model.projection;

import java.time.LocalDate;

public interface OrderSummary {
    Long getId();
    double getTotalAmount();
    LocalDate getCreatedAt();
    String getProcessorName();
}
