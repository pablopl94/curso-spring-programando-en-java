package com.programandoenjava.bootcamp_1_2026.orderItem.infrastructure.database.entity;

import com.programandoenjava.bootcamp_1_2026.order.infraestructure.database.model.entity.OrderEntity;
import com.programandoenjava.bootcamp_1_2026.product.infraestructure.database.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "order_item")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id", nullable = false)
    private Long id;

    @EqualsAndHashCode.Include
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @EqualsAndHashCode.Include
    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

}