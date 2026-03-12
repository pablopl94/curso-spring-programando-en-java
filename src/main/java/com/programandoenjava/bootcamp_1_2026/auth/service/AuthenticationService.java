package com.programandoenjava.bootcamp_1_2026.auth.service;

import com.nimbusds.jose.JOSEException;
import com.programandoenjava.bootcamp_1_2026.auth.dto.TokenResponseDto;
import com.programandoenjava.bootcamp_1_2026.config.application.JwtService;
import com.programandoenjava.bootcamp_1_2026.user.model.application.input.UserInputDto;
import com.programandoenjava.bootcamp_1_2026.user.model.application.output.UserOutputDto;
import com.programandoenjava.bootcamp_1_2026.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;



    @Transactional
    public TokenResponseDto register(UserInputDto userDto) {

        //Verifica que el usuario no existe y devuelve el usuario creado
        UserOutputDto user = userService.createUser(userDto);

        //Creamos el token a partir del usuario y lo devolvemos
        try {
            String token = jwtService.createToken(user);
            return TokenResponseDto.builder().token(token).build();
        } catch (JOSEException e) {
            throw new ServiceException("Error al crear el token", e);
        }
    }

    public TokenResponseDto authenticate(UserInputDto userDto){
        /**
         * Con AuthenticationManager spring ya válida el usuario:
         *  - usa userDetailService para buscar el usuario (si no existe lanza excepción)
         *  - el rol lo he añadido a los claims por lo que Spring cuando validar el JWT lo lee
         *    automáticamente de getAuthorities() de la entidad User
         *  - verifica la contraseña con PasswordEncoder
         */
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getEmail(),
                        userDto.getEmail()
                )
        );

        //Obtenemos el usuario
        UserOutputDto user = userService.getUserByEmail(userDto.getEmail());
        //Generamos el token
        String newToken;

        try{
           newToken = jwtService.createToken(user);
        } catch (JOSEException e) {
            throw new RuntimeException("Error al crear el token " + e);
        }

        return TokenResponseDto.builder()
                .token(newToken)
                .build();
    }
}
