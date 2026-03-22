package com.programandoenjava.bootcamp_1_2026.order.model.api.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderSummaryResponseDto implements OrderResponseDto{
    private Long id;
    private Double totalAmount;
    private String customerEmail;
    private String processorName;
    private LocalDateTime createdAt;
}
