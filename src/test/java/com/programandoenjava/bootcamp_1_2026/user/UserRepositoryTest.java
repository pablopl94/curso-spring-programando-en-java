package com.programandoenjava.bootcamp_1_2026.user;

import com.programandoenjava.bootcamp_1_2026.config.TestContainerConfig;
import com.programandoenjava.bootcamp_1_2026.config.infrastucture.CriteriaBuilderConfig;
import com.programandoenjava.bootcamp_1_2026.user.domain.constants.RoleEnum;
import com.programandoenjava.bootcamp_1_2026.user.domain.entity.Role;
import com.programandoenjava.bootcamp_1_2026.user.domain.entity.User;
import com.programandoenjava.bootcamp_1_2026.user.domain.port.out.RoleRepository;
import com.programandoenjava.bootcamp_1_2026.user.domain.port.out.UserRepository;
import com.programandoenjava.bootcamp_1_2026.user.infrastucture.database.DBRoleAdapter;
import com.programandoenjava.bootcamp_1_2026.user.infrastucture.database.DbUserAdapter;
import com.programandoenjava.bootcamp_1_2026.user.infrastucture.database.mapper.RoleRepositoryMapper;
import com.programandoenjava.bootcamp_1_2026.user.infrastucture.database.mapper.UserRepositoryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({CriteriaBuilderConfig.class, DbUserAdapter.class, DBRoleAdapter.class,
        UserRepositoryMapper.class, RoleRepositoryMapper.class})
@ActiveProfiles("test")
public class UserRepositoryTest extends TestContainerConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private Role role;

    private User createUser(String name, String email, String password) {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .role(role)
                .build();
    }

    @BeforeEach
    void setUp() {
        role = roleRepository.findByName(RoleEnum.USER)
                .orElseGet(() -> roleRepository.save(Role.builder().name(RoleEnum.USER).build()));
    }

    @Test
    @DisplayName("debería guardar un usuario válido")
    void test01_shouldCreateValidUser() {
        //Arrange
        User user = createUser("pablo", "pablo@test.com", "1234");

        //Act
        User saved = userRepository.save(user);

        //Asserts
        assertThat(userRepository.findById(saved.id())).isPresent();
        assertThat(saved.id()).isNotNull();
        assertThat(saved.name()).isEqualTo("pablo");
        assertThat(saved.email()).isEqualTo("pablo@test.com");
        assertThat(saved.role()).isEqualTo(role);
    }

    @Test
    @DisplayName("debería retornar el usuario cuando existe el email")
    void test02_shouldReturnUserWhenEmailExists() {
        //Arrange
        User user = userRepository.save(createUser("pablo", "pablo@test.com", "1234"));

        //Act
        Optional<User> result = userRepository.findByEmail(user.email());

        //Asserts
        assertThat(result).isPresent()
                .hasValueSatisfying(u -> {
                    assertThat(u.name()).isEqualTo("pablo");
                    assertThat(u.email()).isEqualTo("pablo@test.com");
                    assertThat(u.role()).isEqualTo(role);
                });
    }

    @Test
    @DisplayName("debería retornar vacío cuando el email no existe")
    void test03_shouldReturnEmptyWhenEmailNotFound() {
        //Act
        Optional<User> result = userRepository.findByEmail("noexiste@test.com");

        //Assert
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("debería retornar el usuario cuando existe el id")
    void test04_shouldReturnUserWhenIdExists() {
        //Arrange
        User user = userRepository.save(createUser("pablo", "pablo@test.com", "1234"));

        //Act
        Optional<User> result = userRepository.findById(user.id());

        //Asserts
        assertThat(result).isPresent()
                .hasValueSatisfying(u -> {
                    assertThat(u.name()).isEqualTo("pablo");
                    assertThat(u.email()).isEqualTo("pablo@test.com");
                    assertThat(u.role()).isEqualTo(role);
                });
    }

    @Test
    @DisplayName("debería retornar vacío cuando el id no existe")
    void test05_shouldReturnEmptyWhenIdNotFound() {
        //Act
        Optional<User> result = userRepository.findById(999L);

        //Assert
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("debería devolver una lista de usuarios")
    void test06_shouldReturnListOfUsers() {
        //Arrange
        userRepository.save(createUser("pablo", "pablo@test.com", "1234"));
        userRepository.save(createUser("maria", "maria@test.com", "5678"));

        //Act
        List<User> users = userRepository.findAll();

        //Asserts
        assertThat(users).hasSize(2);
    }

    @Test
    @DisplayName("debería devolver una lista vacía si no hay usuarios")
    void test07_shouldReturnEmptyListWhenNoUsers() {
        //Act
        List<User> users = userRepository.findAll();

        //Assert
        assertThat(users).isEmpty();
    }

    @Test
    @DisplayName("debería eliminar el usuario")
    void test08_shouldDeleteExistingUser() {
        //Arrange
        User user = userRepository.save(createUser("pablo", "pablo@test.com", "1234"));

        //Act
        userRepository.deleteById(user.id());

        //Asserts
        Optional<User> deleted = userRepository.findById(user.id());
        assertThat(deleted).isEmpty();
    }
}
