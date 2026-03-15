package com.programandoenjava.bootcamp_1_2026.user;

import com.programandoenjava.bootcamp_1_2026.config.infrastucture.CriteriaBuilderConfig;
import com.programandoenjava.bootcamp_1_2026.user.model.constants.RoleEnum;
import com.programandoenjava.bootcamp_1_2026.user.model.entity.Role;
import com.programandoenjava.bootcamp_1_2026.user.model.entity.User;
import com.programandoenjava.bootcamp_1_2026.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(CriteriaBuilderConfig.class)
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    // Variables de usuario y user
    private Role role;
    private User user;

    @BeforeEach
    void setUp() {
        //Creamos el rol y el usuario que vamos a reutilizar en los test
        role = new Role();
        role.setId(1L);
        role.setName(RoleEnum.USER);

        user = User.builder()
                .role(role)
                .password("pablo")
                .name("pablo")
                .email("pablo@test.com")
                .build();
    }

    @Test
    public void test01_saveUser_successful() {
        //Arrange
        //Ya creamos el usuario y el rol @BeforeEach

        //Act
        User userSaved = userRepository.save(user);

        //Asserts
        assertThat(userRepository.findAll()).hasSize(1);
        assertThat(user.getName()).isEqualTo(userSaved.getName());
        assertThat(user.getEmail()).isEqualTo(userSaved.getEmail());
        assertThat(user.getRole()).isEqualTo(role);
    }

    @Test
    public void test02_findUserByEmail_successful() {
        //Arrange
        //Ya creamos el usuario y el rol @BeforeEach
        userRepository.save(user);

        //Act
        Optional<User> userSaved = userRepository.findByEmail(user.getEmail());

        //Asserts
        assertThat(userRepository.findAll()).hasSize(1);
        assertThat(userSaved).isPresent();
    }
}
