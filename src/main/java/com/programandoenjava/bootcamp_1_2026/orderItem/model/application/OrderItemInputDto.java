package com.programandoenjava.bootcamp_1_2026.orderItem.model.application;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemInputDto {
    private Integer quantity;
    private Double unitPrice;
    private Long idProduct;
}
