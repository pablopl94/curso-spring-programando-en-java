package com.programandoenjava.bootcamp_1_2026.auth.service;

import com.nimbusds.jose.JOSEException;
import com.programandoenjava.bootcamp_1_2026.auth.dto.TokenResponseDto;
import com.programandoenjava.bootcamp_1_2026.config.application.JwtService;
import com.programandoenjava.bootcamp_1_2026.user.exception.RoleNotFoundException;
import com.programandoenjava.bootcamp_1_2026.user.exception.UserAlreadyExistsException;
import com.programandoenjava.bootcamp_1_2026.user.exception.UserNotFoundException;
import com.programandoenjava.bootcamp_1_2026.user.mapper.UserMapper;
import com.programandoenjava.bootcamp_1_2026.user.model.application.input.UserInputDto;
import com.programandoenjava.bootcamp_1_2026.user.model.constants.RoleEnum;
import com.programandoenjava.bootcamp_1_2026.user.model.entity.Role;
import com.programandoenjava.bootcamp_1_2026.user.model.entity.User;
import com.programandoenjava.bootcamp_1_2026.user.repository.RoleRepository;
import com.programandoenjava.bootcamp_1_2026.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;


    public TokenResponseDto register(UserInputDto userDto) {

        try {
            if (userRepository.findByEmail(userDto.email()).isPresent()) {
                throw new UserAlreadyExistsException("Ya existe un usuario con el email: " + userDto.email());
            }

            Role userRole = roleRepository.findByName(RoleEnum.USER).orElseThrow(() -> new RoleNotFoundException(RoleEnum.USER));
            User user = User.builder()
                    .firstname(userDto.firstname())
                    .lastname(userDto.lastname())
                    .email(userDto.email())
                    .password(passwordEncoder.encode(userDto.password()))
                    .role(userRole)
                    .build();

            String newToken;

            try {
                newToken = jwtService.createToken(user);
            } catch (JOSEException e) {
                throw new RuntimeException("Error al crear el token " + e);
            }

            try {
                userRepository.save(user);
            } catch (RuntimeException e) {
                throw new RuntimeException("Error al guarda el usuario " + e);
            }

            return TokenResponseDto.builder()
                    .token(newToken)
                    .build();

        } catch (DataAccessException e) {
            throw new ServiceException("Error al registrar el usuario " + e);
        }
    }

    public TokenResponseDto authenticate(UserInputDto userDto) throws JOSEException {
        /**
         * Con AuthenticationManager spring ya válida el usuario:
         *  - usa userDetailService para buscar el usuario (si no existe lanza excepción)
         *  - el rol lo he añadido a los claims por lo que Spring cuando validar el JWT lo lee
         *    automáticamente de getAuthorities() de la entidad User
         *  - verifica la contraseña con PasswordEncoder
         */
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.email(),
                        userDto.password()
                )
        );

        //Obtiene el usuario completo
        User user = userRepository.findByEmail(userDto.email()).orElseThrow(() -> new UserNotFoundException(userDto.email()));

        //Generamos el token
        String newToken = jwtService.createToken(user);

        return TokenResponseDto.builder()
                .token(newToken)
                .build();
    }
}
