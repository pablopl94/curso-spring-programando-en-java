package com.programandoenjava.bootcamp_1_2026.product.domain.entity;

public record Product(
        Long id,
        String name,
        Double price,
        Integer stock
) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private Double price;
        private Integer stock;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(Double price) {
            this.price = price;
            return this;
        }

        public Builder stock(Integer stock) {
            this.stock = stock;
            return this;
        }

        public Product build() {
            if (name == null || name.isBlank())
                throw new IllegalStateException("El nombre es obligatorio");
            if (price == null || price < 0)
                throw new IllegalStateException("El precio no puede ser negativo");
            if (stock == null || stock < 0)
                throw new IllegalStateException("El stock no puede ser negativo");

            return new Product(id, name, price, stock);
        }
    }

}