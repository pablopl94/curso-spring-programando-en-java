package com.programandoenjava.bootcamp_1_2026.order.infraestructure.database.model.projection;

import java.time.LocalDateTime;

public interface OrderSummaryProjection {

    Long getId();

    double getTotalAmount();

    LocalDateTime getCreatedAt();

    String getProcessorName();

}
