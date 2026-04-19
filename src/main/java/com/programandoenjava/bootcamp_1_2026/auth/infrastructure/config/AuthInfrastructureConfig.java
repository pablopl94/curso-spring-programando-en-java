package com.programandoenjava.bootcamp_1_2026.auth.infrastructure.config;

import com.programandoenjava.bootcamp_1_2026.auth.application.service.AuthenticationService;
import com.programandoenjava.bootcamp_1_2026.auth.domain.port.out.AuthenticatorPort;
import com.programandoenjava.bootcamp_1_2026.auth.domain.port.out.PasswordHasherPort;
import com.programandoenjava.bootcamp_1_2026.auth.domain.port.out.TokenGeneratorPort;
import com.programandoenjava.bootcamp_1_2026.auth.domain.port.out.UserAccessPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthInfrastructureConfig {

    @Bean
    public AuthenticationService authenticationService(
            UserAccessPort userAccessPort,
            TokenGeneratorPort tokenGenerator,
            AuthenticatorPort authenticator,
            PasswordHasherPort passwordHasher
    ) {
        return new AuthenticationService(userAccessPort, tokenGenerator, authenticator, passwordHasher);
    }
}
