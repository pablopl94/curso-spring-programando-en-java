package com.programandoenjava.bootcamp_1_2026.order.infraestructure.database.repository;

import com.programandoenjava.bootcamp_1_2026.order.infraestructure.database.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringJpaOrderRepository extends JpaRepository<OrderEntity, Long> {

    @EntityGraph(attributePaths = "items.product")
    List<OrderEntity> findAll();
}
