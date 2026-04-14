package com.programandoenjava.bootcamp_1_2026.user.application;

import com.programandoenjava.bootcamp_1_2026.user.domain.entity.User;
import com.programandoenjava.bootcamp_1_2026.user.domain.exception.UserNotFoundException;
import com.programandoenjava.bootcamp_1_2026.user.domain.port.in.user.*;
import com.programandoenjava.bootcamp_1_2026.user.domain.port.out.UserRepository;

import java.util.List;

public class UserService implements GetUserUseCase, GetAllUsersUseCase, CreateUserUseCase, UpdateUserUseCase, DeleteUserUseCase, GetUserByEmailUseCase {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getOneUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user, Long id) {
        User originalUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        User updatedUser = User.builder()
                .id(originalUser.id())
                .name(user.name())
                .email(user.email())
                .password(user.password())
                .role(user.role())
                .build();
        return userRepository.save(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);
    }

    @Override
    public User getOneUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }
}
