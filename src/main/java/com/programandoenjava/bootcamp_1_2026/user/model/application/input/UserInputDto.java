package com.programandoenjava.bootcamp_1_2026.user.model.application.input;

public record UserInputDto(
        Long id,
        String firstname,
        String lastname,
        String email,
        String password,
        Long idRole
) { }
