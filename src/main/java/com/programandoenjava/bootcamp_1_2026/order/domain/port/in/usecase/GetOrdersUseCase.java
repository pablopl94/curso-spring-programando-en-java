package com.programandoenjava.bootcamp_1_2026.order.domain.port.in.usecase;

import com.programandoenjava.bootcamp_1_2026.order.domain.entity.OrderQuery;
import com.programandoenjava.bootcamp_1_2026.order.domain.entity.OrderView;

import java.util.List;

public interface GetOrdersUseCase {

    List<? extends OrderView> getOrders(OrderQuery query);
}
