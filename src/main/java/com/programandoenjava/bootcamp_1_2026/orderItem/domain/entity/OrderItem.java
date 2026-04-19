package com.programandoenjava.bootcamp_1_2026.orderItem.domain.entity;

import com.programandoenjava.bootcamp_1_2026.product.domain.entity.Product;

public record OrderItem(
        Long id,
        Integer quantity,
        Double unitPrice,
        Product product,
        Long orderId
) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Integer quantity;
        private Double unitPrice;
        private Product product;
        private Long orderId;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder unitPrice(Double unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public Builder productId(Product product) {
            this.product = product;
            return this;
        }

        public Builder orderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }

        public OrderItem build() {
            if (quantity == null || quantity <= 0) {
                throw new IllegalStateException("La cantidad debe ser mayor a cero");
            }
            if (unitPrice == null || unitPrice < 0) {
                throw new IllegalStateException("El precio unitario no puede ser negativo");
            }
            if (product == null) {
                throw new IllegalStateException("El producto es obligatorio");
            }
            if (orderId == null) {
                throw new IllegalStateException("El ID de la orden es obligatorio");
            }

            return new OrderItem(id, quantity, unitPrice, product, orderId);
        }
    }
}