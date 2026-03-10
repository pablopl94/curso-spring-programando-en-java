package com.programandoenjava.bootcamp_1_2026.user.model.application.output;

import com.programandoenjava.bootcamp_1_2026.order.model.application.output.OrderOutputDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserOutputDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private RoleOutputDto role;
    private List<OrderOutputDto> orders;

}