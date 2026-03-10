package com.programandoenjava.bootcamp_1_2026.orderItem.model.application;

import com.programandoenjava.bootcamp_1_2026.product.model.application.ProductOutputDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderItemOutputDto{
    private Long id;
    private Integer quantity;
    private Double unitPrice;
    private ProductOutputDto product;
}
