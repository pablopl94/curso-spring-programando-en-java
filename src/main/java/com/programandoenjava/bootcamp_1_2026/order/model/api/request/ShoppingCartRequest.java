package com.programandoenjava.bootcamp_1_2026.order.model.api.request;

import com.programandoenjava.bootcamp_1_2026.orderItem.model.api.OrderItemRequestDto;
import java.util.Set;

public record ShoppingCartRequest(
        Set<OrderItemRequestDto> items
) {}