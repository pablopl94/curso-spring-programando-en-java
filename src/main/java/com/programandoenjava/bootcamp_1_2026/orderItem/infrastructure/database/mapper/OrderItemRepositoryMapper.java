package com.programandoenjava.bootcamp_1_2026.orderItem.infrastructure.database.mapper;

import com.programandoenjava.bootcamp_1_2026.orderItem.domain.entity.OrderItem;
import com.programandoenjava.bootcamp_1_2026.orderItem.infrastructure.database.entity.OrderItemEntity;
import com.programandoenjava.bootcamp_1_2026.product.infraestructure.database.mapper.ProductRepositoryMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderItemRepositoryMapper {

    private final ProductRepositoryMapper productRepositoryMapper;

    public OrderItemRepositoryMapper(ProductRepositoryMapper productRepositoryMapper) {
        this.productRepositoryMapper = productRepositoryMapper;
    }

    public OrderItem toDomain(OrderItemEntity entity) {
        if (entity == null) return null;

        return new OrderItem(
                entity.getId(),
                entity.getQuantity(),
                entity.getUnitPrice(),
                productRepositoryMapper.toDomain(entity.getProduct()),
                entity.getOrder() != null ? entity.getOrder().getId() : null
        );
    }

    public OrderItemEntity toEntity(OrderItem domain) {
        if (domain == null) return null;

        return new OrderItemEntity(
                domain.id(),
                domain.quantity(),
                domain.unitPrice(),
                productRepositoryMapper.toEntity(domain.product()),
                null
        );
    }
}
