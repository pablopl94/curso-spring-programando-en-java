package com.programandoenjava.bootcamp_1_2026.order.application.views;

import com.programandoenjava.bootcamp_1_2026.order.domain.entity.OrderView;

import java.util.List;

public interface OrderViewStrategy {

    String viewName();

    List<? extends OrderView> execute();
}
