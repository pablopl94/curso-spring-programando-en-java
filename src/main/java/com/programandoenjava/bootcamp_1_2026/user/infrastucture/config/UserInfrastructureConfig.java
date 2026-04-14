package com.programandoenjava.bootcamp_1_2026.user.infrastucture.config;

import com.programandoenjava.bootcamp_1_2026.user.application.RolService;
import com.programandoenjava.bootcamp_1_2026.user.application.UserService;
import com.programandoenjava.bootcamp_1_2026.user.domain.port.out.RoleRepository;
import com.programandoenjava.bootcamp_1_2026.user.domain.port.out.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserInfrastructureConfig {

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

    @Bean
    public RolService rolService(RoleRepository roleRepository) {
        return new RolService(roleRepository);
    }
}
