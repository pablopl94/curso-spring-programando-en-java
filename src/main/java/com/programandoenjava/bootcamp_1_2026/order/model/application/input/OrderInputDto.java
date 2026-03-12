package com.programandoenjava.bootcamp_1_2026.order.model.application.input;

import com.programandoenjava.bootcamp_1_2026.orderItem.model.application.OrderItemInputDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
public class OrderInputDto {
    private Double totalAmount;
    private String processorName;
    private String customerName;
    private String customerEmail;
    private LocalDateTime createdAt;
    private Set<OrderItemInputDto> items;
}