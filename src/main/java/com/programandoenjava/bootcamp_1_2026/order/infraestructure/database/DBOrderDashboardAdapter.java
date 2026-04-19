package com.programandoenjava.bootcamp_1_2026.order.infraestructure.database;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;
import com.programandoenjava.bootcamp_1_2026.order.domain.entity.OrderDashboard;
import com.programandoenjava.bootcamp_1_2026.order.domain.port.out.OrderDashboardRepository;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.database.mapper.OrderDashboardRepositoryMapper;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.database.model.entity.OrderEntity;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.database.model.view.OrderDashboardView;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DBOrderDashboardAdapter implements OrderDashboardRepository {

    private final CriteriaBuilderFactory cbf;
    private final EntityViewManager evm;
    private final EntityManager em;
    private final OrderDashboardRepositoryMapper orderDashboardRepositoryMapper;

    public DBOrderDashboardAdapter(CriteriaBuilderFactory cbf, EntityViewManager evm, EntityManager em, OrderDashboardRepositoryMapper orderDashboardRepositoryMapper) {
        this.cbf = cbf;
        this.evm = evm;
        this.em = em;
        this.orderDashboardRepositoryMapper = orderDashboardRepositoryMapper;
    }


    @Override
    public List<OrderDashboard> findAll() {

        CriteriaBuilder<OrderEntity> cb = cbf.create(em, OrderEntity.class);

        List<OrderDashboardView> view = evm.applySetting(EntityViewSetting.create(OrderDashboardView.class), cb).getResultList();

        return view.stream()
                .map(orderDashboardRepositoryMapper::toDomain)
                .toList();
    }
}
