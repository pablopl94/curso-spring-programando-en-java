package com.programandoenjava.bootcamp_1_2026.order.infraestructure.database.model.view;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.Mapping;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.database.model.entity.OrderEntity;

@EntityView(OrderEntity.class)
public interface OrderDashboardView {

    @IdMapping
    Long getId();

    String getCustomerName();

    @Mapping("SUM(items.quantity)")
    Long getTotalProducts();
}