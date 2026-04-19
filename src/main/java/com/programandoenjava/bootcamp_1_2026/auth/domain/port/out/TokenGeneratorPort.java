package com.programandoenjava.bootcamp_1_2026.auth.domain.port.out;

import com.programandoenjava.bootcamp_1_2026.user.domain.entity.User;

public interface TokenGeneratorPort {
    String generate(User user);
}
