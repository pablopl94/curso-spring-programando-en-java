package com.programandoenjava.bootcamp_1_2026.orderItem.infrastructure.api.mapper;

import com.programandoenjava.bootcamp_1_2026.orderItem.domain.entity.OrderItem;
import com.programandoenjava.bootcamp_1_2026.orderItem.infrastructure.api.dto.OrderItemRequestDto;
import com.programandoenjava.bootcamp_1_2026.orderItem.infrastructure.api.dto.OrderItemResponseDto;
import com.programandoenjava.bootcamp_1_2026.product.domain.entity.Product;
import com.programandoenjava.bootcamp_1_2026.product.infraestructure.api.mapper.ProductApiMapper;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

@Component
public class OrderItemApiMapper {

    private final EntityManager em;
    private final ProductApiMapper productApiMapper;

    public OrderItemApiMapper(EntityManager em, ProductApiMapper productApiMapper) {
        this.em = em;
        this.productApiMapper = productApiMapper;
    }


    public OrderItem toDomain(OrderItemRequestDto request) {
        if (request == null) return null;

        return new OrderItem(
                null,
                request.quantity(),
                em.getReference(Product.class, request.idProduct()).price(),
                em.getReference(Product.class, request.idProduct()),
                null
        );
    }

    public OrderItemResponseDto toResponse(OrderItem domain) {
        if (domain == null) return null;

        return new OrderItemResponseDto(
                domain.id(),
                domain.quantity(),
                domain.unitPrice(),
                productApiMapper.toResponse(domain.product())
        );
    }
}
