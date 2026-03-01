package com.programandoenjava.bootcamp_1_2026.orders.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "total_amount",nullable = false)
    private Double totalAmount;

    @Column(name = "processor_name", nullable = false, length = 50)
    private String processorName;

    @Column(name = "customer_name", nullable = false, length = 100)
    private String customerName;

    @Column(name = "customer_email", nullable = false, length = 100)
    private String customerEmail;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    @BatchSize(size = 20)
    private Set<OrderItem> items = new LinkedHashSet<>();

    //Constructores
    public Order() {}

    public Order(Long id, double totalAmount, String processorName, String customerName, String customerEmail, LocalDate createdAt, Set<OrderItem> items) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.processorName = processorName;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.createdAt = createdAt;
        this.items = items;
    }

    //Getters
    public Long getId() {
        return id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getProcessorName() {
        return processorName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    //Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTotalAmount(double totaAmount) {
        this.totalAmount = totaAmount;
    }

    public void setProcessorName(String processorName) {
        this.processorName = processorName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }
}
