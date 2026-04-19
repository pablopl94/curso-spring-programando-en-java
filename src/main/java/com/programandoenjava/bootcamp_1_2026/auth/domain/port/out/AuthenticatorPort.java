package com.programandoenjava.bootcamp_1_2026.auth.domain.port.out;

public interface AuthenticatorPort {
    void authenticate(String email, String password);
}
