package com.programandoenjava.bootcamp_1_2026.order.mapper;

import com.programandoenjava.bootcamp_1_2026.common.mapper.GenericMapper;
import com.programandoenjava.bootcamp_1_2026.order.model.api.request.OrderRequestDto;
import com.programandoenjava.bootcamp_1_2026.order.model.api.response.OrderResponseDto;
import com.programandoenjava.bootcamp_1_2026.order.model.api.response.OrderSummaryResponseDto;
import com.programandoenjava.bootcamp_1_2026.order.model.api.response.OrderViewResponseDto;
import com.programandoenjava.bootcamp_1_2026.order.model.application.input.OrderInputDto;
import com.programandoenjava.bootcamp_1_2026.order.model.application.output.OrderOutputDto;
import com.programandoenjava.bootcamp_1_2026.order.model.entity.Order;
import com.programandoenjava.bootcamp_1_2026.order.repository.impl.OrderDashboardView;
import com.programandoenjava.bootcamp_1_2026.order.repository.projection.OrderSummary;
import com.programandoenjava.bootcamp_1_2026.orderItem.mapper.OrderItemMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", uses = {OrderItemMapper.class}, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderMapper extends GenericMapper<Order, OrderRequestDto, OrderResponseDto, OrderInputDto, OrderOutputDto> {

    // OUTPUT SUMMARY → SUMMARY RESPONSE
    OrderSummaryResponseDto outputSummaryToSummaryResponseDto(OrderOutputDto output);

    // PROJECTION → OUTPUT SUMMARY
    @Mapping(target = "customerName", ignore = true)
    @Mapping(target = "customerEmail", ignore = true)
    @Mapping(target = "items", ignore = true)
    @Mapping(target = "processorName", qualifiedByName = "mapProcessorName")
    OrderOutputDto projectionToOutputDto(OrderSummary projection);

    @Named("mapProcessorName")
    default String mapProcessorName(String processorName) {
        return "Pago procesado por: " + processorName;
    }

    // VIEW → OUTPUT VIEW
    OrderViewResponseDto viewToViewResponseDto(OrderDashboardView view);

}