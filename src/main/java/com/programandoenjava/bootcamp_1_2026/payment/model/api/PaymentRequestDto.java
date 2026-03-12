package com.programandoenjava.bootcamp_1_2026.payment.model.api;

import com.programandoenjava.bootcamp_1_2026.orderItem.model.application.OrderItemInputDto;

import java.util.Set;

//Esto le llegaría del front después del checkout
public record PaymentRequestDto(
        Double totalAmount,
        Set<OrderItemInputDto>items
) {}