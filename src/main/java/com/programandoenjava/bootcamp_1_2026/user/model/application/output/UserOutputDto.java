package com.programandoenjava.bootcamp_1_2026.user.model.application.output;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserOutputDto {
    private Long id;
    private String name;
    private String email;
    private RoleOutputDto role;
}