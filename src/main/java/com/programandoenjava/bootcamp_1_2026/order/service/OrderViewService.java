package com.programandoenjava.bootcamp_1_2026.order.service;

import com.programandoenjava.bootcamp_1_2026.order.exception.OrderServiceException;
import com.programandoenjava.bootcamp_1_2026.order.mapper.OrderMapper;
import com.programandoenjava.bootcamp_1_2026.order.model.application.output.OrderSummaryOutputDto;
import com.programandoenjava.bootcamp_1_2026.order.model.application.output.OrderViewOutputDto;
import com.programandoenjava.bootcamp_1_2026.order.repository.OrderRepository;
import com.programandoenjava.bootcamp_1_2026.order.repository.impl.OrderDashboardView;
import com.programandoenjava.bootcamp_1_2026.order.repository.projection.OrderSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class OrderViewService {

    private final OrderRepository repository;
    private final OrderMapper mapper;

    public List<OrderSummaryOutputDto> getSummary() {
        try {
            List<OrderSummary> orderList = repository.findOrderSummaryBy();
            return orderList.stream()
                    .map(this.mapper::projectionToOutputSummaryDto)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new OrderServiceException("OrderService.Order.getSummary", "No se ha podido obtener el sumario de pedidos");
        }
    }

    public List<OrderViewOutputDto> getDashboardView() {
        try {
            List<OrderDashboardView> orderList = repository.findAllDashboard();
            return orderList.stream()
                    .map(this.mapper::viewToOutputViewDto)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new OrderServiceException("OrderService.Order.getDashboardView", "No se ha podido obtener el listado del dashboard view");
        }
    }
}