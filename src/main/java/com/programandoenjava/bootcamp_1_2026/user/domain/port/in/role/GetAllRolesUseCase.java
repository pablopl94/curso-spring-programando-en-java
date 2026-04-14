package com.programandoenjava.bootcamp_1_2026.user.domain.port.in.role;

import com.programandoenjava.bootcamp_1_2026.user.domain.entity.Role;

import java.util.List;

public interface GetAllRolesUseCase {

    List<Role> getAllRoles();
}
