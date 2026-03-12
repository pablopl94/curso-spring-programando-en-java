package com.programandoenjava.bootcamp_1_2026.order.model.api.response;

import com.programandoenjava.bootcamp_1_2026.orderItem.model.api.OrderItemRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class CheckoutResponseDto {
    private Double totalAmount;
    private String emailCustomer;
    private Set<OrderItemRequestDto> items;
}