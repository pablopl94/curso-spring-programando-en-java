package com.programandoenjava.bootcamp_1_2026.user.infrastucture.database.repository;

import com.programandoenjava.bootcamp_1_2026.user.domain.constants.RoleEnum;
import com.programandoenjava.bootcamp_1_2026.user.infrastucture.database.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringJPARoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(RoleEnum name);
}
