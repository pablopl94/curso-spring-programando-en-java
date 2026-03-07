package com.programandoenjava.bootcamp_1_2026.order.repository;

import com.programandoenjava.bootcamp_1_2026.order.model.entity.Order;
import com.programandoenjava.bootcamp_1_2026.order.repository.projection.OrderSummary;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {

    @EntityGraph(attributePaths = "items.product")
    List<Order> findAll();

    List<OrderSummary> findOrderSummaryBy();

}
