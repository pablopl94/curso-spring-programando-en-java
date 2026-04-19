package com.programandoenjava.bootcamp_1_2026.order.infraestructure.database.model.entity;


import com.programandoenjava.bootcamp_1_2026.orderItem.infrastructure.database.entity.OrderItemEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id", nullable = false)
    private Long id;

    @EqualsAndHashCode.Include
    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @EqualsAndHashCode.Include
    @Column(name = "processor_name", nullable = false, length = 50)
    private String processorName;

    @EqualsAndHashCode.Include
    @Column(name = "customer_name", nullable = false, length = 100)
    private String customerName;

    @EqualsAndHashCode.Include
    @Column(name = "customer_email", nullable = false, length = 100)
    private String customerEmail;

    @EqualsAndHashCode.Include
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @BatchSize(size = 20)
    private Set<OrderItemEntity> items = new LinkedHashSet<>();

    @Version
    @Setter(AccessLevel.NONE)
    private Integer version;

    public OrderEntity(Long id, Double totalAmount, String processorName, String customerName, String customerEmail, LocalDateTime createdAt, Set<OrderItemEntity> items) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.processorName = processorName;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.createdAt = createdAt;
        this.items = items;
    }
}
