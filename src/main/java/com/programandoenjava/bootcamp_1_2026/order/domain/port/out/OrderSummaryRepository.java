package com.programandoenjava.bootcamp_1_2026.order.domain.port.out;

import com.programandoenjava.bootcamp_1_2026.order.domain.entity.OrderSummary;

import java.util.List;

public interface OrderSummaryRepository {

    List<OrderSummary> findAllByOrderSummary();
}
