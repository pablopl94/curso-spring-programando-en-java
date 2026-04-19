package com.programandoenjava.bootcamp_1_2026.auth.infrastructure.api.mapper;

import com.programandoenjava.bootcamp_1_2026.auth.domain.model.AuthToken;
import com.programandoenjava.bootcamp_1_2026.auth.domain.model.Credentials;
import com.programandoenjava.bootcamp_1_2026.auth.domain.model.RegisterCommand;
import com.programandoenjava.bootcamp_1_2026.auth.infrastructure.api.dto.in.LoginRequestDto;
import com.programandoenjava.bootcamp_1_2026.auth.infrastructure.api.dto.in.RegisterRequestDto;
import com.programandoenjava.bootcamp_1_2026.auth.infrastructure.api.dto.out.TokenResponseDto;
import org.springframework.stereotype.Component;

@Component
public class AuthApiMapper {

    public Credentials toCredentials(LoginRequestDto request) {
        return new Credentials(request.email(), request.password());
    }

    public RegisterCommand toRegisterCommand(RegisterRequestDto request) {
        return new RegisterCommand(request.name(), request.email(), request.password());
    }

    public TokenResponseDto toResponse(AuthToken authToken) {
        return new TokenResponseDto(authToken.token());
    }
}
