package com.programandoenjava.bootcamp_1_2026.user.model.api.request;

public record UserRequestDto(
        String firstname,
        String lastname,
        String email,
        String password,
        Long idRole
) { }
