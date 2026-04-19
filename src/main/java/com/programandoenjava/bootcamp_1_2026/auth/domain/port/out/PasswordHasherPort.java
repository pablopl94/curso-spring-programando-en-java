package com.programandoenjava.bootcamp_1_2026.auth.domain.port.out;

public interface PasswordHasherPort {
    String hash(String rawPassword);
}
