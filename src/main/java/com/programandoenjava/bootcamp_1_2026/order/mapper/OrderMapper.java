package com.programandoenjava.bootcamp_1_2026.order.mapper;

import com.programandoenjava.bootcamp_1_2026.common.mapper.GenericMapper;
import com.programandoenjava.bootcamp_1_2026.order.model.api.request.OrderRequestDto;
import com.programandoenjava.bootcamp_1_2026.order.model.api.response.OrderResponseDto;
import com.programandoenjava.bootcamp_1_2026.order.model.api.response.OrderSummaryResponseDto;
import com.programandoenjava.bootcamp_1_2026.order.model.api.response.OrderViewResponseDto;
import com.programandoenjava.bootcamp_1_2026.order.model.entity.Order;
import com.programandoenjava.bootcamp_1_2026.order.repository.impl.OrderDashboardView;
import com.programandoenjava.bootcamp_1_2026.order.repository.projection.OrderSummary;
import com.programandoenjava.bootcamp_1_2026.order.model.application.input.OrderInputDto;
import com.programandoenjava.bootcamp_1_2026.order.model.application.output.OrderOutputDto;
import com.programandoenjava.bootcamp_1_2026.order.model.application.output.OrderSummaryOutputDto;
import com.programandoenjava.bootcamp_1_2026.order.model.application.output.OrderViewOutputDto;
import com.programandoenjava.bootcamp_1_2026.product.mapper.ProductMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface OrderMapper extends GenericMapper<Order, OrderRequestDto, OrderResponseDto, OrderInputDto, OrderOutputDto> {

    // REQUEST → INPUT
    @Override
    OrderInputDto requestToInputDto(OrderRequestDto request);

    // INPUT → ENTITY
    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "totalAmount", ignore = true)
    @Mapping(target = "processorName", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Order inputToEntity(OrderInputDto input);

    // ENTITY → OUTPUT
    @Override
    OrderOutputDto entityToOutputDto(Order entity);

    // OUTPUT → RESPONSE
    @Override
    OrderResponseDto outputToResponseDto(OrderOutputDto output);

    // OUTPUT SUMMARY → SUMMARY RESPONSE
    OrderSummaryResponseDto outputSummaryToSummaryResponseDto(OrderSummaryOutputDto output);

    // PROJECTION → OUTPUT SUMMARY
    OrderSummaryOutputDto projectionToOutputSummaryDto(OrderSummary projection);

    // OUTPUT VIEW → VIEW RESPONSE
    OrderViewResponseDto outputViewToViewResponseDto(OrderViewOutputDto output);

    // VIEW → OUTPUT VIRE
    OrderViewOutputDto viewToOutputViewDto(OrderDashboardView view);

}