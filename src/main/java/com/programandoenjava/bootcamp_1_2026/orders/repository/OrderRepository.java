package com.programandoenjava.bootcamp_1_2026.orders.repository;

import com.programandoenjava.bootcamp_1_2026.orders.model.entity.Order;
import com.programandoenjava.bootcamp_1_2026.orders.model.projection.*;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {

    @EntityGraph(attributePaths = "items.product")
    List<Order> findAll();

    //Para probar el BatchSize a solas SIN JOIN
    List<Order> findAllByProcessorNameIgnoreCase(String proccesorName);

    List<OrderSummary> findOrderSummaryBy();

}
