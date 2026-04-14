package com.programandoenjava.bootcamp_1_2026.user.infrastucture.api.dto;

import com.programandoenjava.bootcamp_1_2026.order.infraestructure.api.dto.response.OrderResponseDto;
import com.programandoenjava.bootcamp_1_2026.user.model.application.output.RoleOutputDto;

import java.util.List;

public record UserResponseDto(
        Long id,
        String name,
        String email,
        String password,
        RoleOutputDto role,
        List<OrderResponseDto> orders
) {
}
