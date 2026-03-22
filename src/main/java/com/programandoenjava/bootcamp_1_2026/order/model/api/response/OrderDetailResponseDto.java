package com.programandoenjava.bootcamp_1_2026.order.model.api.response;

import com.programandoenjava.bootcamp_1_2026.orderItem.model.api.OrderItemResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class OrderDetailResponseDto extends OrderSummaryResponseDto {
    private String customerName;
    private Set<OrderItemResponseDto> items;
}
