package com.programandoenjava.bootcamp_1_2026.auth.application.service;

import com.programandoenjava.bootcamp_1_2026.auth.domain.model.AuthToken;
import com.programandoenjava.bootcamp_1_2026.auth.domain.model.Credentials;
import com.programandoenjava.bootcamp_1_2026.auth.domain.model.RegisterCommand;
import com.programandoenjava.bootcamp_1_2026.auth.domain.port.in.LoginUseCase;
import com.programandoenjava.bootcamp_1_2026.auth.domain.port.in.RegisterUseCase;
import com.programandoenjava.bootcamp_1_2026.auth.domain.port.out.AuthenticatorPort;
import com.programandoenjava.bootcamp_1_2026.auth.domain.port.out.PasswordHasherPort;
import com.programandoenjava.bootcamp_1_2026.auth.domain.port.out.TokenGeneratorPort;
import com.programandoenjava.bootcamp_1_2026.auth.domain.port.out.UserAccessPort;
import com.programandoenjava.bootcamp_1_2026.user.domain.entity.User;

public class AuthenticationService implements LoginUseCase, RegisterUseCase {

    private final UserAccessPort userAccessPort;
    private final TokenGeneratorPort tokenGenerator;
    private final AuthenticatorPort authenticator;
    private final PasswordHasherPort passwordHasher;

    public AuthenticationService(
            UserAccessPort userAccessPort,
            TokenGeneratorPort tokenGenerator,
            AuthenticatorPort authenticator,
            PasswordHasherPort passwordHasher
    ) {
        this.userAccessPort = userAccessPort;
        this.tokenGenerator = tokenGenerator;
        this.authenticator = authenticator;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public AuthToken login(Credentials credentials) {
        authenticator.authenticate(credentials.email(), credentials.password());
        User user = userAccessPort.findByEmail(credentials.email());
        return new AuthToken(tokenGenerator.generate(user));
    }

    @Override
    public AuthToken register(RegisterCommand command) {
        String hashedPassword = passwordHasher.hash(command.password());
        User newUser = userAccessPort.register(command.name(), command.email(), hashedPassword);
        return new AuthToken(tokenGenerator.generate(newUser));
    }
}
