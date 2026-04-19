package com.programandoenjava.bootcamp_1_2026.order.domain.port.out;

import com.programandoenjava.bootcamp_1_2026.order.domain.entity.Order;
import com.programandoenjava.bootcamp_1_2026.order.domain.entity.OrderFilter;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Optional<Order> findById(Long id);

    List<Order> findAll();

    Order save(Order order);

    Boolean existsById(Long id);

    void deleteById(Long id);

    List<Order> search(OrderFilter filter);
}
