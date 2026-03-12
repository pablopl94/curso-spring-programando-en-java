package com.programandoenjava.bootcamp_1_2026.user.repository;

import com.programandoenjava.bootcamp_1_2026.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
}
