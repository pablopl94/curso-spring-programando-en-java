package com.programandoenjava.bootcamp_1_2026.order.infraestructure.api.dto.response;


public sealed interface OrderViewResponseDto permits OrderResponseDto, OrderSummaryResponseDto, OrderDashboardResponseDto {
}
