package com.programandoenjava.bootcamp_1_2026.orderItem.infrastructure.database.repository;

import com.programandoenjava.bootcamp_1_2026.orderItem.infrastructure.database.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringJPAOrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

}
