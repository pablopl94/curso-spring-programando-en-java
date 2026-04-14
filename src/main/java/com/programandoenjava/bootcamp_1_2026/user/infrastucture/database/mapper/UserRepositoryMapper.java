package com.programandoenjava.bootcamp_1_2026.user.infrastucture.database.mapper;

import com.programandoenjava.bootcamp_1_2026.user.domain.entity.User;
import com.programandoenjava.bootcamp_1_2026.user.infrastucture.database.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryMapper {

    private final RoleRepositoryMapper roleRepositoryMapper;

    public UserRepositoryMapper(RoleRepositoryMapper roleRepositoryMapper) {
        this.roleRepositoryMapper = roleRepositoryMapper;
    }

    public User toDomain(UserEntity entity) {
        if (entity == null) return null;

        return new User(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPassword(),
                roleRepositoryMapper.toDomain(entity.getRole())
        );
    }

    public UserEntity toEntity(User domain) {
        if (domain == null) return null;

        return new UserEntity(
                domain.id(),
                domain.name(),
                domain.email(),
                domain.password(),
                roleRepositoryMapper.toEntity(domain.role())
        );
    }
}