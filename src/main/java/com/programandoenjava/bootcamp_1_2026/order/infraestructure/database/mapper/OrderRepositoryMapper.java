package com.programandoenjava.bootcamp_1_2026.order.infraestructure.database.mapper;

import com.programandoenjava.bootcamp_1_2026.order.domain.entity.Order;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.database.model.entity.OrderEntity;
import com.programandoenjava.bootcamp_1_2026.orderItem.infrastructure.database.entity.OrderItemEntity;
import com.programandoenjava.bootcamp_1_2026.orderItem.infrastructure.database.mapper.OrderItemRepositoryMapper;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

@Component
public class OrderRepositoryMapper {

    private final OrderItemRepositoryMapper orderItemRepositoryMapper;

    public OrderRepositoryMapper(OrderItemRepositoryMapper orderItemRepositoryMapper) {
        this.orderItemRepositoryMapper = orderItemRepositoryMapper;
    }

    public Order toDomain(OrderEntity entity) {
        if (entity == null) return null;

        return new Order(
                entity.getId(),
                entity.getTotalAmount(),
                entity.getProcessorName(),
                entity.getCustomerName(),
                entity.getCustomerEmail(),
                entity.getCreatedAt(),
                entity.getItems().stream()
                        .map(orderItemRepositoryMapper::toDomain)
                        .collect(Collectors.toSet())
        );
    }

    public OrderEntity toEntity(Order domain) {
        if (domain == null) return null;

        OrderEntity orderEntity = new OrderEntity(
                domain.id(),
                domain.totalAmount(),
                domain.processorName(),
                domain.customerName(),
                domain.customerEmail(),
                domain.createdAt(),
                new LinkedHashSet<>()
        );

        domain.items().forEach(item -> {
            OrderItemEntity itemEntity = orderItemRepositoryMapper.toEntity(item);
            itemEntity.setOrder(orderEntity);
            orderEntity.getItems().add(itemEntity);
        });

        return orderEntity;
    }
}
