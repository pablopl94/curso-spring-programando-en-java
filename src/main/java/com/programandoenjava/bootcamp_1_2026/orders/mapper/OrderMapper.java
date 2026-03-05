package com.programandoenjava.bootcamp_1_2026.orders.mapper;

import com.programandoenjava.bootcamp_1_2026.orders.model.api.request.OrderRequestDto;
import com.programandoenjava.bootcamp_1_2026.orders.model.api.response.OrderResponseDto;
import com.programandoenjava.bootcamp_1_2026.orders.model.api.response.OrderSummaryResponseDto;
import com.programandoenjava.bootcamp_1_2026.orders.model.api.response.OrderViewResponseDto;
import com.programandoenjava.bootcamp_1_2026.orders.model.entity.Order;
import com.programandoenjava.bootcamp_1_2026.orders.model.projection.OrderDashboardView;
import com.programandoenjava.bootcamp_1_2026.orders.model.projection.OrderSummary;
import com.programandoenjava.bootcamp_1_2026.orders.model.service.input.OrderInputDto;
import com.programandoenjava.bootcamp_1_2026.orders.model.service.output.OrderOutputDto;
import com.programandoenjava.bootcamp_1_2026.orders.model.service.output.OrderSummaryOutputDto;
import com.programandoenjava.bootcamp_1_2026.orders.model.service.output.OrderViewOutputDto;
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