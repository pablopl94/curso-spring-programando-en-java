package com.programandoenjava.bootcamp_1_2026.order.infraestructure.api.mapper;

import com.programandoenjava.bootcamp_1_2026.order.domain.entity.Order;
import com.programandoenjava.bootcamp_1_2026.order.domain.entity.OrderDashboard;
import com.programandoenjava.bootcamp_1_2026.order.domain.entity.OrderFilter;
import com.programandoenjava.bootcamp_1_2026.order.domain.entity.OrderSummary;
import com.programandoenjava.bootcamp_1_2026.order.domain.entity.OrderView;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.api.dto.request.RequestOrderFilter;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.api.dto.response.OrderDashboardResponseDto;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.api.dto.response.OrderResponseDto;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.api.dto.response.OrderSummaryResponseDto;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.api.dto.response.OrderViewResponseDto;
import com.programandoenjava.bootcamp_1_2026.orderItem.infrastructure.api.mapper.OrderItemApiMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderApiMapper {

    private final OrderItemApiMapper orderItemApiMapper;

    public OrderApiMapper(OrderItemApiMapper orderItemApiMapper) {
        this.orderItemApiMapper = orderItemApiMapper;
    }

    public OrderFilter toDomain(RequestOrderFilter request) {
        if (request == null) return null;
        return new OrderFilter(
                request.getCreatedAtFrom(),
                request.getCreatedAtTo(),
                request.getTotalAmountMin(),
                request.getTotalAmountMax(),
                request.getProductName()
        );
    }

    public OrderViewResponseDto toResponse(OrderView view) {
        if (view == null) return null;
        return switch (view) {
            case Order o -> toOrderResponse(o);
            case OrderSummary s -> toSummaryResponse(s);
            case OrderDashboard d -> toDashboardResponse(d);
        };
    }

    private OrderResponseDto toOrderResponse(Order o) {
        return new OrderResponseDto(
                o.id(),
                o.totalAmount(),
                o.processorName(),
                o.customerName(),
                o.customerEmail(),
                o.createdAt(),
                o.items().stream()
                        .map(orderItemApiMapper::toResponse)
                        .collect(Collectors.toSet())
        );
    }

    private OrderSummaryResponseDto toSummaryResponse(OrderSummary s) {
        return new OrderSummaryResponseDto(s.id(), s.totalAmount(), s.processorName(), s.createdAt());
    }

    private OrderDashboardResponseDto toDashboardResponse(OrderDashboard d) {
        return new OrderDashboardResponseDto(d.id(), d.customerName(), d.totalProducts());
    }
}
