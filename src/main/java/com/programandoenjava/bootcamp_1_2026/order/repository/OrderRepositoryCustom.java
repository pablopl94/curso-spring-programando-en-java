package com.programandoenjava.bootcamp_1_2026.order.repository;


import com.programandoenjava.bootcamp_1_2026.order.model.entity.Order;
import com.programandoenjava.bootcamp_1_2026.order.repository.impl.OrderDashboardView;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepositoryCustom {

    List<Order> findOrderByFilters(
            LocalDateTime createdAtFrom,
            LocalDateTime createdAtTo,
            Double totalAmountMin,
            Double totalAmountMax,
            String productName
    );

    List<OrderDashboardView> findAllDashboard();
}
