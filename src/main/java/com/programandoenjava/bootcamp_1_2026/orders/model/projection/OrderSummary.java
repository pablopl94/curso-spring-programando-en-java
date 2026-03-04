package com.programandoenjava.bootcamp_1_2026.orders.model.projection;

import java.time.LocalDateTime;

public interface OrderSummary {
    Long getId();
    double getTotalAmount();
    LocalDateTime getCreatedAt();
    String getProcessorName();
}
