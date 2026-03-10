package com.programandoenjava.bootcamp_1_2026.order.model.api.response;

import com.programandoenjava.bootcamp_1_2026.orderItem.model.api.OrderItemResponseDto;

import java.util.Set;

public class OrderDetailResponseDto extends OrderSummaryResponseDto{
    private Set<OrderItemResponseDto> items;
}
