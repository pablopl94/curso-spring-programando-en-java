package com.programandoenjava.bootcamp_1_2026.user.model.application.output;

import com.programandoenjava.bootcamp_1_2026.order.model.application.output.OrderOutputDto;

import java.util.List;

public record UserOutputDto(
        Long id,
        String firstname,
        String lastname,
        String email,
        String password,
        RoleOutputDto role,
        List<OrderOutputDto> orders
) { }
