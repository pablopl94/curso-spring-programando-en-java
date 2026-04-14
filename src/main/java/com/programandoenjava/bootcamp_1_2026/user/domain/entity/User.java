package com.programandoenjava.bootcamp_1_2026.user.domain.entity;

public record User(
        Long id,
        String name,
        String email,
        String password,
        Role role
) {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String email;
        private String password;
        private Role role;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public User build() {
            if (name == null || name.isBlank())
                throw new IllegalStateException("El nombre es obligatorio");
            if (email == null || email.isBlank())
                throw new IllegalStateException("El email es obligatorio");
            if (password == null || password.isBlank())
                throw new IllegalStateException("La contraseña es obligatoria");
            if (role == null)
                throw new IllegalStateException("El rol es obligatorio");

            return new User(id, name, email, password, role);
        }
    }
}
