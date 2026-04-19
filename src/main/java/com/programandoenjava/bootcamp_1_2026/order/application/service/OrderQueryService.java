package com.programandoenjava.bootcamp_1_2026.order.application.service;

import com.programandoenjava.bootcamp_1_2026.order.application.views.OrderViewStrategy;
import com.programandoenjava.bootcamp_1_2026.order.domain.entity.OrderQuery;
import com.programandoenjava.bootcamp_1_2026.order.domain.entity.OrderView;
import com.programandoenjava.bootcamp_1_2026.order.domain.port.in.usecase.GetOrdersUseCase;
import com.programandoenjava.bootcamp_1_2026.order.domain.port.out.OrderRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderQueryService implements GetOrdersUseCase {

    private final OrderRepository orderRepository;
    private final Map<String, OrderViewStrategy> views;

    public OrderQueryService(OrderRepository orderRepository, List<OrderViewStrategy> strategies) {
        this.orderRepository = orderRepository;
        this.views = strategies.stream()
                .collect(Collectors.toMap(OrderViewStrategy::viewName, s -> s));
    }

    @Override
    public List<? extends OrderView> getOrders(OrderQuery query) {
        if (query.hasFilter()) {
            return orderRepository.search(query.filter());
        }
        if (query.hasView()) {
            OrderViewStrategy strategy = views.get(query.view());
            if (strategy == null) {
                throw new IllegalArgumentException("Vista desconocida: " + query.view());
            }
            return strategy.execute();
        }
        return orderRepository.findAll();
    }
}
