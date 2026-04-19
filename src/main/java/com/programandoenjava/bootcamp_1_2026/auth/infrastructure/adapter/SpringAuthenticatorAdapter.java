package com.programandoenjava.bootcamp_1_2026.auth.infrastructure.adapter;

import com.programandoenjava.bootcamp_1_2026.auth.domain.exception.InvalidCredentialsException;
import com.programandoenjava.bootcamp_1_2026.auth.domain.port.out.AuthenticatorPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringAuthenticatorAdapter implements AuthenticatorPort {

    private final AuthenticationManager authenticationManager;

    @Override
    public void authenticate(String email, String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
        } catch (AuthenticationException e) {
            throw new InvalidCredentialsException();
        }
    }
}
