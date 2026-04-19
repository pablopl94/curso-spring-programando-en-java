package com.programandoenjava.bootcamp_1_2026.user.domain.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long userId) {
        super("Usuario no encontrado con id: " + userId);
    }

    public UserNotFoundException(String email) {
        super("Usuario no encontrado con email: " + email);
    }
}
