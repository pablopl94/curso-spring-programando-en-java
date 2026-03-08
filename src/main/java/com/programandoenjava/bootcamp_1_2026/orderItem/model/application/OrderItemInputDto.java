package com.programandoenjava.bootcamp_1_2026.orderItem.model.application;

import com.programandoenjava.bootcamp_1_2026.product.model.application.ProductOutputDto;

public record OrderItemInputDto(
        Integer quantity,
        Double unitPrice,
        ProductOutputDto product
) { }
