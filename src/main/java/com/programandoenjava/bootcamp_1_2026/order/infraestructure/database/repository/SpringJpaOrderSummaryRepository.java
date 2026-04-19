package com.programandoenjava.bootcamp_1_2026.order.infraestructure.database.repository;

import com.programandoenjava.bootcamp_1_2026.order.infraestructure.database.model.entity.OrderEntity;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.database.model.projection.OrderSummaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringJpaOrderSummaryRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT o FROM OrderEntity o")
    List<OrderSummaryProjection> findAllByOrderSummary();
}
