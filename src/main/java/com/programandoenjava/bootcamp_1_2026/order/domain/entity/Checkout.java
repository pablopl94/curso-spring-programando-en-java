package com.programandoenjava.bootcamp_1_2026.order.domain.entity;

import com.programandoenjava.bootcamp_1_2026.orderItem.domain.entity.OrderItem;

import java.util.Set;

public record Checkout(
        String emailCustomer,
        Double totalPrice,
        Set<OrderItem> items
) {

    public Double calculateTotalPrice() {
        double precioTotal = 0;
        for (OrderItem item : this.items) {
            precioTotal = precioTotal + ((item.quantity()) * (item.unitPrice()));
        }
        return precioTotal = Math.round(precioTotal * 100.0) / 100.0;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String emailCustomer;
        private Double totalPrice;
        private Set<OrderItem> items;

        public Builder emailCustomer(String emailCustomer) {
            this.emailCustomer = emailCustomer;
            return this;
        }

        public Builder totalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder items(Set<OrderItem> items) {
            this.items = items;
            return this;
        }

        public Checkout build() {
            if (emailCustomer == null || emailCustomer.isBlank())
                throw new IllegalStateException("El email del cliente es obligatorio");
            if (totalPrice == null || totalPrice < 0)
                throw new IllegalStateException("El precio total no puede ser negativo");
            if (items == null || items.isEmpty())
                throw new IllegalStateException("El checkout debe tener al menos un item");

            return new Checkout(emailCustomer, totalPrice, items);
        }
    }
}
