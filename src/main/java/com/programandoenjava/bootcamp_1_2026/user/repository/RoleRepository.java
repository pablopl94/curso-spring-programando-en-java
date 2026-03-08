package com.programandoenjava.bootcamp_1_2026.user.repository;

import com.programandoenjava.bootcamp_1_2026.user.model.constants.RoleEnum;
import com.programandoenjava.bootcamp_1_2026.user.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleEnum name);
}
