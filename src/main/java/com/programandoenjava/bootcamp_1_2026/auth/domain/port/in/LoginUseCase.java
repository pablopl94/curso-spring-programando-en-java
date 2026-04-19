package com.programandoenjava.bootcamp_1_2026.auth.domain.port.in;

import com.programandoenjava.bootcamp_1_2026.auth.domain.model.AuthToken;
import com.programandoenjava.bootcamp_1_2026.auth.domain.model.Credentials;

public interface LoginUseCase {
    AuthToken login(Credentials credentials);
}
