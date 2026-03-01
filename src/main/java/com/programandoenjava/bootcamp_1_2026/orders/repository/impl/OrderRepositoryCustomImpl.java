package com.programandoenjava.bootcamp_1_2026.orders.repository.impl;

import com.programandoenjava.bootcamp_1_2026.orders.model.entity.Order;
import com.programandoenjava.bootcamp_1_2026.orders.model.entity.*;
import com.programandoenjava.bootcamp_1_2026.orders.repository.OrderRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {

    private final EntityManager em;

    public OrderRepositoryCustomImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Order> findOrderByFilters(LocalDate createdAtFrom, LocalDate createdAtTo, Double totalAmountMin, Double totalAmountMax, String productName) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> order = cq.from(Order.class);

        List<Predicate> predicates = new ArrayList<>();

        if(createdAtFrom != null) {
            predicates.add(cb.greaterThanOrEqualTo(order.<LocalDate>get(Order_.createdAt), createdAtFrom));
        }

        if(createdAtTo != null) {
            predicates.add(cb.lessThanOrEqualTo(order.<LocalDate>get(Order_.createdAt), createdAtTo));
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
}
