package com.programandoenjava.bootcamp_1_2026.orderItem.model.application;

import com.programandoenjava.bootcamp_1_2026.product.application.dto.out.ProductOutput;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemOutputDto {
    private Long id;
    private Integer quantity;
    private Double unitPrice;
    private ProductOutput product;
}
