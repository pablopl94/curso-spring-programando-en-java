package com.programandoenjava.bootcamp_1_2026.auth.controller;

import com.programandoenjava.bootcamp_1_2026.auth.dto.LoginRequestDto;
import com.programandoenjava.bootcamp_1_2026.auth.dto.RegisterRequestDto;
import com.programandoenjava.bootcamp_1_2026.auth.dto.TokenResponseDto;
import com.programandoenjava.bootcamp_1_2026.auth.service.AuthenticationService;
import com.programandoenjava.bootcamp_1_2026.user.mapper.UserMapper;
import com.programandoenjava.bootcamp_1_2026.user.model.application.input.UserInputDto;
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

    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<TokenResponseDto> register(@RequestBody RegisterRequestDto request){

        UserInputDto inputDto = userMapper.registerRequestToInput(request);

        return ResponseEntity.ok().body(authenticationService.register(inputDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequestDto request){

        UserInputDto inputDto = userMapper.loginRequestToInput(request);
        return ResponseEntity.ok().body(authenticationService.authenticate(inputDto));
    }
}
