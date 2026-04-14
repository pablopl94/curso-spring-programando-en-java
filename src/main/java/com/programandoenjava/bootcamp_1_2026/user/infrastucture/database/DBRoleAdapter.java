package com.programandoenjava.bootcamp_1_2026.user.infrastucture.database;

import com.programandoenjava.bootcamp_1_2026.user.domain.constants.RoleEnum;
import com.programandoenjava.bootcamp_1_2026.user.domain.entity.Role;
import com.programandoenjava.bootcamp_1_2026.user.domain.port.out.RoleRepository;
import com.programandoenjava.bootcamp_1_2026.user.infrastucture.database.entity.RoleEntity;
import com.programandoenjava.bootcamp_1_2026.user.infrastucture.database.mapper.RoleRepositoryMapper;
import com.programandoenjava.bootcamp_1_2026.user.infrastucture.database.repository.SpringJPARoleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DBRoleAdapter implements RoleRepository {

    private final SpringJPARoleRepository springJpaRoleRepository;
    private final RoleRepositoryMapper roleRepositoryMapper;

    public DBRoleAdapter(SpringJPARoleRepository springJpaRoleRepository, RoleRepositoryMapper roleRepositoryMapper) {
        this.springJpaRoleRepository = springJpaRoleRepository;
        this.roleRepositoryMapper = roleRepositoryMapper;
    }

    @Override
    public Optional<Role> findById(Long id) {
        return springJpaRoleRepository.findById(id).map(roleRepositoryMapper::toDomain);
    }

    @Override
    public Optional<Role> findByName(RoleEnum name) {
        return springJpaRoleRepository.findByName(name).map(roleRepositoryMapper::toDomain);
    }

    @Override
    public List<Role> findAll() {
        return springJpaRoleRepository.findAll().stream().map(roleRepositoryMapper::toDomain).toList();
    }

    @Override
    public Role save(Role role) {
        RoleEntity roleEntity = roleRepositoryMapper.toEntity(role);
        RoleEntity savedEntity = springJpaRoleRepository.save(roleEntity);
        return roleRepositoryMapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        springJpaRoleRepository.deleteById(id);
    }
}
