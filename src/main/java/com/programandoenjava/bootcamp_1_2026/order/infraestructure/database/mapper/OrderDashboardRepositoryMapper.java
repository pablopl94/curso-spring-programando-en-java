package com.programandoenjava.bootcamp_1_2026.order.infraestructure.database.mapper;

import com.programandoenjava.bootcamp_1_2026.order.domain.entity.OrderDashboard;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.database.model.view.OrderDashboardView;
import org.springframework.stereotype.Component;

@Component
public class OrderDashboardRepositoryMapper {

    public OrderDashboard toDomain(OrderDashboardView view) {
        if (view == null) return null;

        return new OrderDashboard(
                view.getId(),
                view.getCustomerName(),
                view.getTotalProducts()
        );
    }

}
