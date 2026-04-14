package com.programandoenjava.bootcamp_1_2026.user.infrastucture.api.dto;

public record UserRequestDto(
        String name,
        String email,
        String password,
        Long idRole
) {
}
