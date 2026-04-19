package com.programandoenjava.bootcamp_1_2026.auth.domain.model;

public record Credentials(String email, String password) {
    public Credentials {
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("El email es obligatorio");
        if (password == null || password.isBlank())
            throw new IllegalArgumentException("La password es obligatoria");
    }
}
