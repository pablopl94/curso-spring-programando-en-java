package com.programandoenjava.bootcamp_1_2026.user.mapper;

import com.programandoenjava.bootcamp_1_2026.auth.dto.LoginRequestDto;
import com.programandoenjava.bootcamp_1_2026.auth.dto.RegisterRequestDto;
import com.programandoenjava.bootcamp_1_2026.common.mapper.GenericMapper;
import com.programandoenjava.bootcamp_1_2026.order.mapper.OrderMapper;
import com.programandoenjava.bootcamp_1_2026.user.model.api.request.UserRequestDto;
import com.programandoenjava.bootcamp_1_2026.user.model.api.response.UserResponseDto;
import com.programandoenjava.bootcamp_1_2026.user.model.application.input.UserInputDto;
import com.programandoenjava.bootcamp_1_2026.user.model.application.output.UserOutputDto;
import com.programandoenjava.bootcamp_1_2026.user.model.entity.Role;
import com.programandoenjava.bootcamp_1_2026.user.model.entity.User;
import com.programandoenjava.bootcamp_1_2026.user.repository.RoleRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {OrderMapper.class})
public abstract class UserMapper implements GenericMapper<User, UserRequestDto, UserResponseDto, UserInputDto, UserOutputDto> {

    @Autowired
    protected RoleRepository roleRepository;

    @Override
    @Mapping(source = "idRole", target = "role", qualifiedByName = "mapRol")
    @Mapping(target = "order", ignore = true)
    public abstract User inputToEntity(UserInputDto input);

    @Named("mapRol")
    protected Role mapRol(Long idRole) {
        if (idRole == null) return null;
        return roleRepository.getReferenceById(idRole);
    }

    public abstract UserInputDto registerRequestToInput(RegisterRequestDto dto);

    public abstract UserInputDto loginRequestToInput(LoginRequestDto dto);

}
