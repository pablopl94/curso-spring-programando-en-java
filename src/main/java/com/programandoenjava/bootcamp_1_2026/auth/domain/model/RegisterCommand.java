package com.programandoenjava.bootcamp_1_2026.auth.domain.model;

public record RegisterCommand(
        String name,
        String email,
        String password
) {
    public RegisterCommand {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("El nombre es obligatorio");
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("El email es obligatorio");
        if (password == null || password.isBlank())
            throw new IllegalArgumentException("La password es obligatoria");
    }
}
