package com.programandoenjava.bootcamp_1_2026.user.application;

import com.programandoenjava.bootcamp_1_2026.user.domain.constants.RoleEnum;
import com.programandoenjava.bootcamp_1_2026.user.domain.entity.Role;
import com.programandoenjava.bootcamp_1_2026.user.domain.exception.RoleNotFoundException;
import com.programandoenjava.bootcamp_1_2026.user.domain.port.in.role.*;
import com.programandoenjava.bootcamp_1_2026.user.domain.port.out.RoleRepository;

import java.util.List;

public class RolService implements GetDefaultRoleUseCase, GetRoleUseCase, GetAllRolesUseCase, CreateRoleUseCase, UpdateRoleUseCase, DeleteRoleUseCase {

    private final RoleRepository roleRepository;

    public RolService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getDefaultRole() {
        return roleRepository.findByName(RoleEnum.USER)
                .orElseThrow(() -> new RoleNotFoundException(RoleEnum.USER));
    }

    @Override
    public Role getOneRole(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException(id));
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Role role, Long id) {
        roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
        Role updatedRole = Role.builder()
                .id(id)
                .name(role.name())
                .build();
        return roleRepository.save(updatedRole);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
        roleRepository.deleteById(id);
    }
}
