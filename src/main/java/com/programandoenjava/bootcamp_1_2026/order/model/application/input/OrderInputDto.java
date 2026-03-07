package com.programandoenjava.bootcamp_1_2026.order.model.application.input;

import com.programandoenjava.bootcamp_1_2026.orderItem.model.application.OrderItemInputDto;

import java.util.Set;

public record OrderInputDto(
        String customerName,
        String customerEmail,
        Set<OrderItemInputDto> items
) { }
