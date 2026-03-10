package com.programandoenjava.bootcamp_1_2026.order.model.api.response;

import java.time.LocalDateTime;

public class OrderSummaryResponseDto implements OrderResponseDto{
    Long id;
    Double totalAmount;
    String customerEmail;
    String processorName;
    LocalDateTime createdAt;
}
