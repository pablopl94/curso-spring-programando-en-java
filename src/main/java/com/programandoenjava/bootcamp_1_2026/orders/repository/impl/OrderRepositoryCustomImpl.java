package com.programandoenjava.bootcamp_1_2026.orders.repository.impl;

import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;
import com.programandoenjava.bootcamp_1_2026.orders.model.entity.Order;
import com.programandoenjava.bootcamp_1_2026.orders.model.entity.*;
import com.programandoenjava.bootcamp_1_2026.orders.model.projection.OrderDashboardView;
import com.programandoenjava.bootcamp_1_2026.orders.repository.OrderRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {

    private final EntityManager em;
    private final CriteriaBuilderFactory cbf;
    private final EntityViewManager evm;

    public OrderRepositoryCustomImpl(EntityManager em, CriteriaBuilderFactory cbf, EntityViewManager evm, EntityManager em1) {
        this.em = em;
        this.cbf = cbf;
        this.evm = evm;
    }

    @Override
    public List<Order> findOrderByFilters(LocalDateTime createdAtFrom, LocalDateTime createdAtTo, Double totalAmountMin, Double totalAmountMax, String productName) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> order = cq.from(Order.class);

        List<Predicate> predicates = new ArrayList<>();

        if(createdAtFrom != null) {
            predicates.add(cb.greaterThanOrEqualTo(order.<LocalDateTime>get(Order_.createdAt), createdAtFrom));
        }

        if(createdAtTo != null) {
            predicates.add(cb.lessThanOrEqualTo(order.<LocalDateTime>get(Order_.createdAt), createdAtTo));
        }

        if(totalAmountMin != null) {
            predicates.add(cb.greaterThanOrEqualTo(order.<Double>get(Order_.totalAmount), totalAmountMin));
        }
        if(totalAmountMax != null) {
            predicates.add(cb.lessThanOrEqualTo(order.<Double>get(Order_.totalAmount), totalAmountMax));
        }

        if (productName != null && !productName.isBlank()) {
            Join<Order, OrderItem> itemJoin = order.join(Order_.items, JoinType.LEFT);
            Join<OrderItem, Product> productJoin = itemJoin.join(OrderItem_.product, JoinType.LEFT);
            predicates.add(cb.like(productJoin.<String>get(Product_.name), "%" + productName + "%"));
        }

        // Uso un fetch ahora, para cargar los datos en la entidad Order, el join solo filtra.
        // Me ha dado problemas usando solo el fetch para filtrar, esta es la única manera que he conseguido
        // de que me funcione correctamente. Sin esto también funciona porque esta el BatchSize en las entidades,
        //pero hace 3 consultas en total y con esto 1. He medido los tiempos y el resultado y he visto esta más eficiente.
        order.fetch(Order_.items, JoinType.LEFT).fetch(OrderItem_.product, JoinType.LEFT);

        // Validación menos debe haber UN filtro
        if(predicates.isEmpty()){
            throw new IllegalArgumentException("Debe proporcionar al menos un criterio de búsqueda");
        }

        // Elimino duplicados con distinct que pueden aparecer por los joins
        cq.select(order).distinct(true).where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }

    public List<OrderDashboardView> findAllDashboard() {

        com.blazebit.persistence.CriteriaBuilder<Order> cb = cbf.create(em, Order.class);

        List<OrderDashboardView> view = evm.applySetting(EntityViewSetting.create(OrderDashboardView.class), cb).getResultList();

        return view;
    }
}
