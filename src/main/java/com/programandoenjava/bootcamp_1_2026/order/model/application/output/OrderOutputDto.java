package com.programandoenjava.bootcamp_1_2026.order.model.application.output;

import com.programandoenjava.bootcamp_1_2026.orderItem.model.application.OrderItemOutputDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderOutputDto {
    private Long id;
    private Double totalAmount;
    private String processorName;
    private String customerName;
    private String customerEmail;
    private LocalDateTime createdAt;
    private Set<OrderItemOutputDto> items;
}