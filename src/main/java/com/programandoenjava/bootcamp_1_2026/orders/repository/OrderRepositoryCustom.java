package com.programandoenjava.bootcamp_1_2026.orders.repository;


import com.programandoenjava.bootcamp_1_2026.orders.model.entity.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepositoryCustom {

    List<Order> findOrderByFilters(
            LocalDate createdAtFrom,
            LocalDate createdAtTo,
            Double totalAmountMin,
            Double totalAmountMax,
            String productName
    );
}
