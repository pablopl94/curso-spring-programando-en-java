package com.programandoenjava.bootcamp_1_2026.order.application.service;

import com.programandoenjava.bootcamp_1_2026.order.domain.entity.Order;
import com.programandoenjava.bootcamp_1_2026.order.domain.exception.OrderNotFoundException;
import com.programandoenjava.bootcamp_1_2026.order.domain.port.in.usecase.CreateOrderUseCase;
import com.programandoenjava.bootcamp_1_2026.order.domain.port.in.usecase.DeleteOneOrderUseCase;
import com.programandoenjava.bootcamp_1_2026.order.domain.port.in.usecase.GetOneOrderUseCase;
import com.programandoenjava.bootcamp_1_2026.order.domain.port.out.OrderRepository;
import com.programandoenjava.bootcamp_1_2026.user.domain.entity.User;

import java.time.LocalDateTime;

public class OrderService implements CreateOrderUseCase, DeleteOneOrderUseCase, GetOneOrderUseCase {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order, User user) {
        Order newOrder = Order.builder()
                .customerEmail(user.email())
                .customerName(user.name())
                .processorName(order.processorName())
                .items(order.items())
                .createdAt(LocalDateTime.now())
                .build();

        return orderRepository.save(newOrder);
    }

    @Override
    public void deleteOneOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order getOneOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }
}
