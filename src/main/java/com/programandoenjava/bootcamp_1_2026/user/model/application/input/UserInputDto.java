package com.programandoenjava.bootcamp_1_2026.user.model.application.input;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserInputDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Long idRole;

}