package com.programandoenjava.bootcamp_1_2026.user.infrastucture.database.mapper;

import com.programandoenjava.bootcamp_1_2026.user.domain.entity.Role;
import com.programandoenjava.bootcamp_1_2026.user.infrastucture.database.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleRepositoryMapper {

    public Role toDomain(RoleEntity entity) {
        if (entity == null) return null;

        return new Role(
                entity.getId(),
                entity.getName()
        );
    }

    public RoleEntity toEntity(Role domain) {
        if (domain == null) return null;

        return new RoleEntity(
                domain.id(),
                domain.name()
        );
    }
}