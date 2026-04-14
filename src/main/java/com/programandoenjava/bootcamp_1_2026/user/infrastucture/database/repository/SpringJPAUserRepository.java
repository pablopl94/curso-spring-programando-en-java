package com.programandoenjava.bootcamp_1_2026.user.infrastucture.database.repository;

import com.programandoenjava.bootcamp_1_2026.user.infrastucture.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringJPAUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
}
