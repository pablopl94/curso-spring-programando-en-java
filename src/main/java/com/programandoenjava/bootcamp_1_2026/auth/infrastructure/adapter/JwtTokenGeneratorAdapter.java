package com.programandoenjava.bootcamp_1_2026.auth.infrastructure.adapter;

import com.nimbusds.jose.JOSEException;
import com.programandoenjava.bootcamp_1_2026.auth.domain.exception.TokenGenerationException;
import com.programandoenjava.bootcamp_1_2026.auth.domain.port.out.TokenGeneratorPort;
import com.programandoenjava.bootcamp_1_2026.config.application.JwtService;
import com.programandoenjava.bootcamp_1_2026.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenGeneratorAdapter implements TokenGeneratorPort {

    private final JwtService jwtService;

    @Override
    public String generate(User user) {
        try {
            return jwtService.createToken(user);
        } catch (JOSEException e) {
            throw new TokenGenerationException(e);
        }
    }
}
