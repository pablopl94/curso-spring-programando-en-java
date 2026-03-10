package com.programandoenjava.bootcamp_1_2026.user.model.application.output;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserOutputDto {
    private Long id;
    private String name;
    private String email;
    private RoleOutputDto role;
}