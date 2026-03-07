package com.programandoenjava.bootcamp_1_2026.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record LoginRequestDto(

        @NotNull(message = "El correo electrónico es obligatorio")
        @NotBlank(message = "El correo electrónico no puede estar vacío")
        @Email(message = "El correo electrónico no tiene un formato válido")
        String email,

        @NotNull(message = "La contraseña es obligatoria")
        @NotBlank(message = "La contraseña no puede estar vacía")
        @Length(min = 5, message = "La contraseña debe tener al menos 5 caracteres")
        String password

) {}