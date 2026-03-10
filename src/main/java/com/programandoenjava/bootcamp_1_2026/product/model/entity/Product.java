package com.programandoenjava.bootcamp_1_2026.product.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id", nullable = false)
    private Long id;

    @EqualsAndHashCode.Include
    @Column(name = "name", nullable = false)
    private String name;

    @EqualsAndHashCode.Include
    @Column(name = "price", nullable = false)
    private Double price;

    @EqualsAndHashCode.Include
    @Column(name = "stock", nullable = false)
    private Integer stock;

}
