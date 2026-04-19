package com.programandoenjava.bootcamp_1_2026.order.application.views.commands;

import com.programandoenjava.bootcamp_1_2026.order.application.views.OrderViewStrategy;
import com.programandoenjava.bootcamp_1_2026.order.domain.entity.OrderSummary;
import com.programandoenjava.bootcamp_1_2026.order.domain.port.out.OrderSummaryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderSummaryViewStrategy implements OrderViewStrategy {

    private final OrderSummaryRepository orderSummaryRepository;

    public OrderSummaryViewStrategy(OrderSummaryRepository orderSummaryRepository) {
        this.orderSummaryRepository = orderSummaryRepository;
    }

    @Override
    public String viewName() {
        return "summary";
    }

    @Override
    public List<OrderSummary> execute() {
        return orderSummaryRepository.findAllByOrderSummary();
    }
}
