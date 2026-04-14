package com.programandoenjava.bootcamp_1_2026.user.infrastucture.database;

import com.programandoenjava.bootcamp_1_2026.user.domain.entity.User;
import com.programandoenjava.bootcamp_1_2026.user.domain.port.out.UserRepository;
import com.programandoenjava.bootcamp_1_2026.user.infrastucture.database.entity.UserEntity;
import com.programandoenjava.bootcamp_1_2026.user.infrastucture.database.mapper.UserRepositoryMapper;
import com.programandoenjava.bootcamp_1_2026.user.infrastucture.database.repository.SpringJPAUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DbUserAdapter implements UserRepository {

    private final SpringJPAUserRepository springJpaUserRepository;
    private final UserRepositoryMapper userRepositoryMapper;

    public DbUserAdapter(SpringJPAUserRepository springJpaUserRepository, UserRepositoryMapper userRepositoryMapper) {
        this.springJpaUserRepository = springJpaUserRepository;
        this.userRepositoryMapper = userRepositoryMapper;
    }

    @Override
    public Optional<User> findById(Long id) {
        return springJpaUserRepository.findById(id).map(userRepositoryMapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return springJpaUserRepository.findAll().stream().map(userRepositoryMapper::toDomain).toList();
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userRepositoryMapper.toEntity(user);
        UserEntity savedEntity = springJpaUserRepository.save(userEntity);
        return userRepositoryMapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        springJpaUserRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return springJpaUserRepository.findByEmail(email).map(userRepositoryMapper::toDomain);
    }
}
