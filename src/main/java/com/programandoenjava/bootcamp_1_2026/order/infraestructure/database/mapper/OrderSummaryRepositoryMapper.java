package com.programandoenjava.bootcamp_1_2026.order.infraestructure.database.mapper;

import com.programandoenjava.bootcamp_1_2026.order.domain.entity.OrderSummary;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.database.model.projection.OrderSummaryProjection;
import org.springframework.stereotype.Component;

@Component
public class OrderSummaryRepositoryMapper {

    public OrderSummary toDomain(OrderSummaryProjection projection) {
        if (projection == null) return null;

        return new OrderSummary(
                projection.getId(),
                projection.getTotalAmount(),
                projection.getProcessorName(),
                projection.getCreatedAt()
        );
    }

}
