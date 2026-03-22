package com.programandoenjava.bootcamp_1_2026.order.model.entity;

import com.programandoenjava.bootcamp_1_2026.orderItem.model.entity.OrderItem;
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
public class Order {

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
    private Set<OrderItem> items = new LinkedHashSet<>();

    @Version
    private Integer version;
}
