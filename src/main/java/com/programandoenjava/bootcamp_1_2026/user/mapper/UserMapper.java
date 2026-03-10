package com.programandoenjava.bootcamp_1_2026.user.mapper;

import com.programandoenjava.bootcamp_1_2026.auth.dto.LoginRequestDto;
import com.programandoenjava.bootcamp_1_2026.auth.dto.RegisterRequestDto;
import com.programandoenjava.bootcamp_1_2026.common.mapper.GenericMapper;
import com.programandoenjava.bootcamp_1_2026.user.model.api.request.UserRequestDto;
import com.programandoenjava.bootcamp_1_2026.user.model.api.response.UserResponseDto;
import com.programandoenjava.bootcamp_1_2026.user.model.application.input.UserInputDto;
import com.programandoenjava.bootcamp_1_2026.user.model.application.output.UserOutputDto;
import com.programandoenjava.bootcamp_1_2026.user.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends GenericMapper<User, UserRequestDto, UserResponseDto, UserInputDto, UserOutputDto> {

    UserInputDto registerRequestToInput(RegisterRequestDto dto);

    UserInputDto loginRequestToInput(LoginRequestDto dto);
}
