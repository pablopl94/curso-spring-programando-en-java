package com.programandoenjava.bootcamp_1_2026.payment.model.application;

import com.programandoenjava.bootcamp_1_2026.orderItem.model.application.OrderItemInputDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class PaymentInputDto {
    private String customerEmail;
    private Double totalAmount;
    private String paymentProvider;
    private Set<OrderItemInputDto> items;
}
