package com.programandoenjava.bootcamp_1_2026.orderItem.domain.port.in;

import com.programandoenjava.bootcamp_1_2026.orderItem.domain.entity.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository {

    Optional<OrderItem> findById(Object o);

    List<OrderItem> findAll();

    OrderItem save(OrderItem item);

    void deleteById(Long id);
}
