package com.programandoenjava.bootcamp_1_2026.order.domain.port.in.usecase;

import com.programandoenjava.bootcamp_1_2026.order.domain.entity.Order;
import com.programandoenjava.bootcamp_1_2026.user.domain.entity.User;

public interface CreateOrderUseCase {

    Order createOrder(Order order, User user);
}
