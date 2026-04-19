package com.programandoenjava.bootcamp_1_2026.auth.domain.exception;

public class TokenGenerationException extends RuntimeException {
    public TokenGenerationException(Throwable cause) {
        super("Error al generar el token", cause);
    }
}
