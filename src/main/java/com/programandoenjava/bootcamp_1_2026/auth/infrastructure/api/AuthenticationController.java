package com.programandoenjava.bootcamp_1_2026.auth.infrastructure.api;

import com.programandoenjava.bootcamp_1_2026.auth.domain.model.AuthToken;
import com.programandoenjava.bootcamp_1_2026.auth.domain.port.in.LoginUseCase;
import com.programandoenjava.bootcamp_1_2026.auth.domain.port.in.RegisterUseCase;
import com.programandoenjava.bootcamp_1_2026.auth.infrastructure.api.dto.in.LoginRequestDto;
import com.programandoenjava.bootcamp_1_2026.auth.infrastructure.api.dto.in.RegisterRequestDto;
import com.programandoenjava.bootcamp_1_2026.auth.infrastructure.api.dto.out.TokenResponseDto;
import com.programandoenjava.bootcamp_1_2026.auth.infrastructure.api.mapper.AuthApiMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final LoginUseCase loginUseCase;
    private final RegisterUseCase registerUseCase;
    private final AuthApiMapper authApiMapper;

    @PostMapping("/register")
    public ResponseEntity<TokenResponseDto> register(@Valid @RequestBody RegisterRequestDto request) {
        AuthToken token = registerUseCase.register(authApiMapper.toRegisterCommand(request));
        return ResponseEntity.ok(authApiMapper.toResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@Valid @RequestBody LoginRequestDto request) {
        AuthToken token = loginUseCase.login(authApiMapper.toCredentials(request));
        return ResponseEntity.ok(authApiMapper.toResponse(token));
    }
}
