package com.programandoenjava.bootcamp_1_2026.order.domain.port.out;

import com.programandoenjava.bootcamp_1_2026.order.domain.entity.OrderDashboard;

import java.util.List;

public interface OrderDashboardRepository {

    List<OrderDashboard> findAll();
}
