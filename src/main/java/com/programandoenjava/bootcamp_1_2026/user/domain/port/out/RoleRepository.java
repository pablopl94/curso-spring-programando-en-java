package com.programandoenjava.bootcamp_1_2026.user.domain.port.out;

import com.programandoenjava.bootcamp_1_2026.user.domain.constants.RoleEnum;
import com.programandoenjava.bootcamp_1_2026.user.domain.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {

    Optional<Role> findById(Long id);

    Optional<Role> findByName(RoleEnum name);

    List<Role> findAll();

    Role save(Role role);

    void deleteById(Long id);
}
