package com.programandoenjava.bootcamp_1_2026.user.exception;

import com.programandoenjava.bootcamp_1_2026.user.model.constants.RoleEnum;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(RoleEnum name) {
        super("Rol no encontrado con el nombre: " + name);
    }

}
