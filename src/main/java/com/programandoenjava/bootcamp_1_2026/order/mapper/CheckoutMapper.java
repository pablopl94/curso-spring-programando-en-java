package com.programandoenjava.bootcamp_1_2026.order.mapper;

import com.programandoenjava.bootcamp_1_2026.order.model.api.request.CheckoutRequestDto;
import com.programandoenjava.bootcamp_1_2026.order.model.api.response.CheckoutResponseDto;
import com.programandoenjava.bootcamp_1_2026.order.model.application.input.CheckoutInputDto;
import com.programandoenjava.bootcamp_1_2026.order.model.application.output.CheckoutOutputDto;
import com.programandoenjava.bootcamp_1_2026.orderItem.mapper.OrderItemMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", uses = {OrderItemMapper.class}, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CheckoutMapper {

    CheckoutInputDto requestToInput(CheckoutRequestDto request);

    CheckoutOutputDto inputToOutput(CheckoutInputDto input);

    CheckoutResponseDto outputToResponse(CheckoutOutputDto output);
}