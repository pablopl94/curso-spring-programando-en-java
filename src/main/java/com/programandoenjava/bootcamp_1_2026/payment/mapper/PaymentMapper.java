package com.programandoenjava.bootcamp_1_2026.payment.mapper;

import com.programandoenjava.bootcamp_1_2026.orderItem.mapper.OrderItemMapper;
import com.programandoenjava.bootcamp_1_2026.payment.model.api.PaymentRequestDto;
import com.programandoenjava.bootcamp_1_2026.payment.model.api.PaymentResponseDto;
import com.programandoenjava.bootcamp_1_2026.payment.model.application.PaymentInputDto;
import com.programandoenjava.bootcamp_1_2026.payment.model.application.PaymentOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class}, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {

    PaymentInputDto requestToInput(PaymentRequestDto request);

    PaymentOutputDto inputToOutput(PaymentInputDto input);

    PaymentResponseDto outputToResponse(PaymentOutputDto output);

}