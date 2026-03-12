package com.programandoenjava.bootcamp_1_2026.order.model.application.input;

import com.programandoenjava.bootcamp_1_2026.orderItem.model.application.OrderItemInputDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class CheckoutInputDto {
    private String emailCustomer;
    private Double totalPrice;
    private Set<OrderItemInputDto> items;
}