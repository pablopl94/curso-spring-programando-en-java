package com.programandoenjava.bootcamp_1_2026.order.model.application.output;

import com.programandoenjava.bootcamp_1_2026.orderItem.model.api.OrderItemRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class CheckoutOutputDto {
    private String emailCustomer;
    private Double totalAmount;
    private Set<OrderItemRequestDto> items;
}