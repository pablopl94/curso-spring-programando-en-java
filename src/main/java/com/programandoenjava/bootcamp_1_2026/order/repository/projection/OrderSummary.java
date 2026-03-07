package com.programandoenjava.bootcamp_1_2026.order.repository.projection;

import java.time.LocalDateTime;

public interface OrderSummary {
    Long getId();
    double getTotalAmount();
    LocalDateTime getCreatedAt();
    String getProcessorName();
}
