package com.programandoenjava.bootcamp_1_2026.orders.service;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;
import com.programandoenjava.bootcamp_1_2026.orders.mapper.OrderMapper;
import com.programandoenjava.bootcamp_1_2026.orders.model.entity.Order;
import com.programandoenjava.bootcamp_1_2026.orders.model.projection.OrderDashboardView;
import com.programandoenjava.bootcamp_1_2026.orders.model.service.output.OrderViewOutputDto;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDashboardService {

    private final CriteriaBuilderFactory cbf;
    private final EntityViewManager evm;
    private final EntityManager em;
    private final OrderMapper mapper;

    public OrderDashboardService(CriteriaBuilderFactory cbf, EntityViewManager evm, EntityManager em, OrderMapper mapper) {
        this.cbf = cbf;
        this.evm = evm;
        this.em = em;
        this.mapper= mapper;
    }

    public List<OrderViewOutputDto> getDashboard() {

        CriteriaBuilder<Order> cb = cbf.create(em, Order.class);

        List<OrderDashboardView> view = evm.applySetting(EntityViewSetting.create(OrderDashboardView.class), cb).getResultList();

        return view.stream()
                .map(mapper::viewToOutputViewDto)
                .toList();
    }
}