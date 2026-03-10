package com.programandoenjava.bootcamp_1_2026.user.service;

import com.programandoenjava.bootcamp_1_2026.user.exception.RoleNotFoundException;
import com.programandoenjava.bootcamp_1_2026.user.model.constants.RoleEnum;
import com.programandoenjava.bootcamp_1_2026.user.model.entity.Role;
import com.programandoenjava.bootcamp_1_2026.user.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolService {

    private final RoleRepository roleRepository;

    public Role getDefaultRole(){

        Role userRole = roleRepository.findByName(RoleEnum.USER)
                .orElseThrow(() -> new RoleNotFoundException(RoleEnum.USER));

        return userRole;
    }

}
