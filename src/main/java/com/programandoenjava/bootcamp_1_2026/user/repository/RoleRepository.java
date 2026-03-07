package com.programandoenjava.bootcamp_1_2026.user.repository;

import com.programandoenjava.bootcamp_1_2026.user.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
