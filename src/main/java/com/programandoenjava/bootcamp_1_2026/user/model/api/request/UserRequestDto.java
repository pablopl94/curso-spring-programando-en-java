package com.programandoenjava.bootcamp_1_2026.user.model.api.request;

public record UserRequestDto(
        String name,
        String email,
        String password,
        Long idRole
) { }
