package com.programandoenjava.bootcamp_1_2026.order.domain.entity;


public sealed interface OrderView permits Order, OrderSummary, OrderDashboard {
}
