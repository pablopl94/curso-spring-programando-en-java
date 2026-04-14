package com.programandoenjava.bootcamp_1_2026.user.domain.port.out;

import com.programandoenjava.bootcamp_1_2026.user.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);

    List<User> findAll();

    User save(User user);

    void deleteById(Long id);

    Optional<User> findByEmail(String email);
}
