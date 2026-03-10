package com.programandoenjava.bootcamp_1_2026.orderItem.model.application;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderItemInputDto{
    private Integer quantity;
    private Double unitPrice;
    private Long idProduct;
}
