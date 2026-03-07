package com.programandoenjava.bootcamp_1_2026.orderItem.model.api;

import com.programandoenjava.bootcamp_1_2026.product.model.api.ProductResponseDto;

public record OrderItemResponseDto(
        Long id,
        Integer quantity,
        Double unitPrice,
        ProductResponseDto product
) { }
