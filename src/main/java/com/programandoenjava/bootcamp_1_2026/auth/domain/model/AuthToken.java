package com.programandoenjava.bootcamp_1_2026.auth.domain.model;

public record AuthToken(String token) {
    public AuthToken {
        if (token == null || token.isBlank())
            throw new IllegalArgumentException("El token es obligatorio");
    }
}
