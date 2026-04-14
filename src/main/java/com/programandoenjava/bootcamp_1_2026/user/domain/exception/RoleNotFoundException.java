package com.programandoenjava.bootcamp_1_2026.user.domain.exception;

import com.programandoenjava.bootcamp_1_2026.user.domain.constants.RoleEnum;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(RoleEnum name) {
        super("Rol no encontrado con el nombre: " + name);
    }

    public RoleNotFoundException(Long id) {
        super("Rol no encontrado con el id: " + id);
    }

}
