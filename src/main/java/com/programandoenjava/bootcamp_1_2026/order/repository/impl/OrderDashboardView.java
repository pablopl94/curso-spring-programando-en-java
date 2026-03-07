package com.programandoenjava.bootcamp_1_2026.order.repository.impl;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.Mapping;
import com.programandoenjava.bootcamp_1_2026.order.model.entity.Order;

@EntityView(Order.class)
public interface OrderDashboardView {

    @IdMapping
    Long getId();

    String getCustomerName();

    @Mapping("SUM(items.quantity)")
    Long getTotalProducts();
}