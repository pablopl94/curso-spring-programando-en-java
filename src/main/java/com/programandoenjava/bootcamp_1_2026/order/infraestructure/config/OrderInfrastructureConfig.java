package com.programandoenjava.bootcamp_1_2026.order.infraestructure.config;

import com.programandoenjava.bootcamp_1_2026.order.application.service.CheckoutService;
import com.programandoenjava.bootcamp_1_2026.order.application.service.OrderQueryService;
import com.programandoenjava.bootcamp_1_2026.order.application.service.OrderService;
import com.programandoenjava.bootcamp_1_2026.order.application.views.OrderViewStrategy;
import com.programandoenjava.bootcamp_1_2026.order.domain.port.out.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OrderInfrastructureConfig {

    @Bean
    public CheckoutService checkoutService() {
        return new CheckoutService();
    }

    @Bean
    public OrderService orderService(OrderRepository orderRepository) {
        return new OrderService(orderRepository);
    }

    @Bean
    public OrderQueryService orderQueryService(OrderRepository orderRepository, List<OrderViewStrategy> strategies) {
        return new OrderQueryService(orderRepository, strategies);
    }
}
