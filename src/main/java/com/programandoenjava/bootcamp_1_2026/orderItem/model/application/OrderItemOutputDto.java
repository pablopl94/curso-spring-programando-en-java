package com.programandoenjava.bootcamp_1_2026.orderItem.model.application;

import com.programandoenjava.bootcamp_1_2026.product.model.application.ProductOutputDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemOutputDto{
    private Long id;
    private Integer quantity;
    private Double unitPrice;
    private ProductOutputDto product;
}
