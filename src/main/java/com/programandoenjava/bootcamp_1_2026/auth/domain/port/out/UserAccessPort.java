package com.programandoenjava.bootcamp_1_2026.auth.domain.port.out;

import com.programandoenjava.bootcamp_1_2026.user.domain.entity.User;

public interface UserAccessPort {

    User findByEmail(String email);

    User register(String name, String email, String hashedPassword);
}
