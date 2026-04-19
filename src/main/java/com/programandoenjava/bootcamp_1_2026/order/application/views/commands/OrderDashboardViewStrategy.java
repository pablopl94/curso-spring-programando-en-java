package com.programandoenjava.bootcamp_1_2026.order.application.views.commands;

import com.programandoenjava.bootcamp_1_2026.order.application.views.OrderViewStrategy;
import com.programandoenjava.bootcamp_1_2026.order.domain.entity.OrderDashboard;
import com.programandoenjava.bootcamp_1_2026.order.domain.port.out.OrderDashboardRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDashboardViewStrategy implements OrderViewStrategy {

    private final OrderDashboardRepository orderDashboardRepository;

    public OrderDashboardViewStrategy(OrderDashboardRepository orderDashboardRepository) {
        this.orderDashboardRepository = orderDashboardRepository;
    }

    @Override
    public String viewName() {
        return "dashboard";
    }

    @Override
    public List<OrderDashboard> execute() {
        return orderDashboardRepository.findAll();
    }
}
