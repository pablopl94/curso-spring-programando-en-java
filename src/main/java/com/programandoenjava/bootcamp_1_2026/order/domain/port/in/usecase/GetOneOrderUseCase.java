package com.programandoenjava.bootcamp_1_2026.order.domain.port.in.usecase;

import com.programandoenjava.bootcamp_1_2026.order.domain.entity.Order;

public interface GetOneOrderUseCase {

    Order getOneOrder(Long id);
}
