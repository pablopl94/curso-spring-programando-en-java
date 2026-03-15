package com.programandoenjava.bootcamp_1_2026.user.service;

import com.programandoenjava.bootcamp_1_2026.user.exception.UserAlreadyExistsException;
import com.programandoenjava.bootcamp_1_2026.user.exception.UserNotFoundException;
import com.programandoenjava.bootcamp_1_2026.user.mapper.UserMapper;
import com.programandoenjava.bootcamp_1_2026.user.model.application.input.UserInputDto;
import com.programandoenjava.bootcamp_1_2026.user.model.application.output.UserOutputDto;
import com.programandoenjava.bootcamp_1_2026.user.model.entity.User;
import com.programandoenjava.bootcamp_1_2026.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RolService rolService;

    public UserOutputDto getUserByEmail(String email) {
        //Obtiene el usuario
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
        //Devolvemos el usuario con el dto del servicio
        return userMapper.entityToOutputDto(user);
    }

    public UserOutputDto createUser(UserInputDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Ya existe un usuario con el email: " + userDto.getEmail());
        }
        //Creamos el usuario, encriptando la contraseña y le añadimos los params recibidos
        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .role(rolService.getDefaultRole())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();
        //Guardamos el usuario
        userRepository.save(user);
        //Devolvemos el usuario con el dto del servicio
        return userMapper.entityToOutputDto(user);
    }
}
