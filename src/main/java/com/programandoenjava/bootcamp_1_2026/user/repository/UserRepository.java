package com.programandoenjava.bootcamp_1_2026.user.repository;

import com.programandoenjava.bootcamp_1_2026.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
