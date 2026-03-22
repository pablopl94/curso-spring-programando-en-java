package com.programandoenjava.bootcamp_1_2026.user;

import com.programandoenjava.bootcamp_1_2026.config.EnableDatabaseTest;
import com.programandoenjava.bootcamp_1_2026.config.TestContainerConfig;
import com.programandoenjava.bootcamp_1_2026.config.infrastucture.CriteriaBuilderConfig;
import com.programandoenjava.bootcamp_1_2026.user.model.constants.RoleEnum;
import com.programandoenjava.bootcamp_1_2026.user.model.entity.Role;
import com.programandoenjava.bootcamp_1_2026.user.model.entity.User;
import com.programandoenjava.bootcamp_1_2026.user.repository.RoleRepository;
import com.programandoenjava.bootcamp_1_2026.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {UserRepository.class, RoleRepository.class, CriteriaBuilderConfig.class})
@EnableDatabaseTest
@Transactional
public class UserRepositoryTest extends TestContainerConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TestEntityManager entityManager;

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
        role = new Role();
        role.setId(1L);
        role.setName(RoleEnum.USER);
        role = roleRepository.save(role);
    }

    @Test
    @DisplayName("debería guardar un usuario válido")
    void test01_shouldCreateValidUser() {
        //Arrange
        User user = createUser("pablo", "pablo@test.com", "1234");

        //Act
        User saved = userRepository.save(user);

        //Asserts
        assertThat(userRepository.findById(saved.getId())).isPresent();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("pablo");
        assertThat(saved.getEmail()).isEqualTo("pablo@test.com");
        assertThat(saved.getRole()).isEqualTo(role);
    }

    @Test
    @DisplayName("debería retornar el usuario cuando existe el email")
    void test02_shouldReturnUserWhenEmailExists() {
        //Arrange
        User user = entityManager.persistAndFlush(
                createUser("pablo", "pablo@test.com", "1234"));

        //Act
        Optional<User> result = userRepository.findByEmail(user.getEmail());

        //Asserts
        assertThat(result).isPresent()
                .hasValueSatisfying(u -> {
                    assertThat(u.getName()).isEqualTo("pablo");
                    assertThat(u.getEmail()).isEqualTo("pablo@test.com");
                    assertThat(u.getRole()).isEqualTo(role);
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
        User user = entityManager.persistAndFlush(
                createUser("pablo", "pablo@test.com", "1234"));

        //Act
        Optional<User> result = userRepository.findById(user.getId());

        //Asserts
        assertThat(result).isPresent()
                .hasValueSatisfying(u -> {
                    assertThat(u.getName()).isEqualTo("pablo");
                    assertThat(u.getEmail()).isEqualTo("pablo@test.com");
                    assertThat(u.getRole()).isEqualTo(role);
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
        entityManager.persistAndFlush(createUser("pablo", "pablo@test.com", "1234"));
        entityManager.persistAndFlush(createUser("maria", "maria@test.com", "5678"));

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
        User user = entityManager.persistAndFlush(
                createUser("pablo", "pablo@test.com", "1234"));

        //Act
        userRepository.deleteById(user.getId());
        entityManager.flush();
        entityManager.clear();

        //Asserts
        Optional<User> deleted = userRepository.findById(user.getId());
        assertThat(deleted).isEmpty();
    }
}