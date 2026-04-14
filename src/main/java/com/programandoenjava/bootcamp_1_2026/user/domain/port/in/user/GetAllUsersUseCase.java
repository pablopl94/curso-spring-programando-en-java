package com.programandoenjava.bootcamp_1_2026.user.domain.port.in.user;

import com.programandoenjava.bootcamp_1_2026.user.domain.entity.User;

import java.util.List;

public interface GetAllUsersUseCase {

    List<User> getAllUsers();
}
