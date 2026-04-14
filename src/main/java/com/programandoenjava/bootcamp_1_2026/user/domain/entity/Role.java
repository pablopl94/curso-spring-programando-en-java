package com.programandoenjava.bootcamp_1_2026.user.domain.entity;

import com.programandoenjava.bootcamp_1_2026.user.domain.constants.RoleEnum;

public record Role(Long id, RoleEnum name) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private RoleEnum name;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(RoleEnum name) {
            this.name = name;
            return this;
        }

        public Role build() {
            if (name == null)
                throw new IllegalStateException("El nombre del rol es obligatorio");

            return new Role(id, name);
        }
    }
}
