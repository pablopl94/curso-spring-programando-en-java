package com.programandoenjava.bootcamp_1_2026.order.infraestructure.api.mapper;

import com.programandoenjava.bootcamp_1_2026.order.domain.entity.Checkout;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.api.dto.request.CartRequestDto;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.api.dto.response.CheckoutResponseDto;
import com.programandoenjava.bootcamp_1_2026.orderItem.infrastructure.api.mapper.OrderItemApiMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CheckoutApiMapper {

    private final OrderItemApiMapper orderItemApiMapper;

    public CheckoutApiMapper(OrderItemApiMapper orderItemApiMapper) {
        this.orderItemApiMapper = orderItemApiMapper;
    }


    public Checkout toDomain(CartRequestDto request) {
        if (request == null) return null;

        return new Checkout(
                null,
                null,
                request.items().stream()
                        .map(orderItemApiMapper::toDomain)
                        .collect(Collectors.toSet())
        );
    }

    public CheckoutResponseDto toResponse(Checkout domain) {
        if (domain == null) return null;

        return new CheckoutResponseDto(
                domain.totalPrice(),
                domain.emailCustomer(),
                domain.items().stream()
                        .map(orderItemApiMapper::toResponse)
                        .collect(Collectors.toSet())
        );
    }
}
