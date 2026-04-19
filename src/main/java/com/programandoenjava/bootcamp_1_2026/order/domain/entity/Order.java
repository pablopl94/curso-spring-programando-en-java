package com.programandoenjava.bootcamp_1_2026.order.domain.entity;

import com.programandoenjava.bootcamp_1_2026.orderItem.domain.entity.OrderItem;

import java.time.LocalDateTime;
import java.util.Set;

public record Order(
        Long id,
        Double totalAmount,
        String processorName,
        String customerName,
        String customerEmail,
        LocalDateTime createdAt,
        Set<OrderItem> items

) implements OrderView {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Double totalAmount;
        private String processorName;
        private String customerName;
        private String customerEmail;
        private LocalDateTime createdAt;
        private Set<OrderItem> items;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder totalAmount(Double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder processorName(String processorName) {
            this.processorName = processorName;
            return this;
        }

        public Builder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Builder customerEmail(String customerEmail) {
            this.customerEmail = customerEmail;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder items(Set<OrderItem> items) {
            this.items = items;
            return this;
        }

        public Order build() {
            if (totalAmount == null || totalAmount < 0)
                throw new IllegalStateException("El monto total no puede ser negativo");
            if (processorName == null || processorName.isBlank())
                throw new IllegalStateException("El nombre del procesador es obligatorio");
            if (customerName == null || customerName.isBlank())
                throw new IllegalStateException("El nombre del cliente es obligatorio");
            if (customerEmail == null || customerEmail.isBlank())
                throw new IllegalStateException("El email del cliente es obligatorio");
            if (createdAt == null)
                throw new IllegalStateException("La fecha de creación es obligatoria");
            if (items == null || items.isEmpty())
                throw new IllegalStateException("La orden debe tener al menos un item");

            return new Order(id, totalAmount, processorName, customerName, customerEmail, createdAt, items);
        }
    }
}
