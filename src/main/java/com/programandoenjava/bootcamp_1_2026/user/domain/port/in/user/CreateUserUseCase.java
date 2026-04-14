package com.programandoenjava.bootcamp_1_2026.user.domain.port.in.user;

import com.programandoenjava.bootcamp_1_2026.user.domain.entity.User;

public interface CreateUserUseCase {

    User createUser(User user);
}
