package com.programandoenjava.bootcamp_1_2026.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokenResponseDto{
    private String token;
}
