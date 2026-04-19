package com.programandoenjava.bootcamp_1_2026.auth.infrastructure.adapter;

import com.programandoenjava.bootcamp_1_2026.auth.domain.port.out.UserAccessPort;
import com.programandoenjava.bootcamp_1_2026.user.domain.entity.Role;
import com.programandoenjava.bootcamp_1_2026.user.domain.entity.User;
import com.programandoenjava.bootcamp_1_2026.user.domain.port.in.role.GetDefaultRoleUseCase;
import com.programandoenjava.bootcamp_1_2026.user.domain.port.in.user.CreateUserUseCase;
import com.programandoenjava.bootcamp_1_2026.user.domain.port.in.user.GetUserByEmailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAccessAdapter implements UserAccessPort {

    private final GetUserByEmailUseCase getUserByEmailUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final GetDefaultRoleUseCase getDefaultRoleUseCase;

    @Override
    public User findByEmail(String email) {
        return getUserByEmailUseCase.getOneUserByEmail(email);
    }

    @Override
    public User register(String name, String email, String hashedPassword) {
        Role role = getDefaultRoleUseCase.getDefaultRole();
        User user = User.builder()
                .name(name)
                .email(email)
                .password(hashedPassword)
                .role(role)
                .build();
        return createUserUseCase.createUser(user);
    }
}
