package com.programandoenjava.bootcamp_1_2026.config.infrastucture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Desactiva protección crsf, solo afecta a sesiones con cookies
                .csrf(AbstractHttpConfigurer::disable)
                // Le dice a Spring que no cree sesiones Http, cada petición ya se autentica por sí sola con token
                // el servidor no guardara ningún usuario
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        //Login y register
                        .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                        //Orders
                        .requestMatchers(HttpMethod.POST, "/api/order/checkout").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/order/**").hasRole("ADMIN")
                        //Payments
                        .requestMatchers(HttpMethod.POST, "/api/payments").authenticated()
                        //Usuarios
                        .requestMatchers("/api/users/**").hasRole("ADMIN")
                        //OrderItems
                        .requestMatchers(HttpMethod.GET,"/api/order-items/**").authenticated()
                        .requestMatchers(HttpMethod.POST,"/api/order-items/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/order-items/**").hasRole("ADMIN")
                        //Productos
                        .requestMatchers(HttpMethod.GET,"/api/products/**").authenticated()
                        .requestMatchers(HttpMethod.POST,"/api/products/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/products/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                //Esto activa el filtro de Resource Server
                // Resource Server lleva el filtro de Spring automático, no hace falta crea un filtro
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder(@Value("${spring.security.oauth2.resourceserver.jwt.secret-key}")String secret){
        //Definimos cómo codificar (usando nuestra clave Nimbus/Secret)
        SecretKey secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        return NimbusJwtDecoder
                .withSecretKey(secretKey)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
    }


}
