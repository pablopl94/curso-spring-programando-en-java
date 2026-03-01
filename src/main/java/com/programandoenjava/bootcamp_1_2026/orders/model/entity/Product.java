package com.programandoenjava.bootcamp_1_2026.orders.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.BatchSize;

@Entity
@Table(name = "product")
@BatchSize(size = 20)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    //Constructores
    public Product(){};

    public Product(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    };

    //Getters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    //Setters

    public void setId(Long id) {
        this.id = id;
    }
}
