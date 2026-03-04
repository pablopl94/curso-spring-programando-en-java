package com.programandoenjava.bootcamp_1_2026.orders.service;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.view.EntityViewSetting;
import com.programandoenjava.bootcamp_1_2026.exceptions.ApiException;
import com.programandoenjava.bootcamp_1_2026.exceptions.OrderNotFoundException;
import com.programandoenjava.bootcamp_1_2026.orders.mapper.OrderMapper;
import com.programandoenjava.bootcamp_1_2026.orders.model.api.request.RequestOrderFilter;
import com.programandoenjava.bootcamp_1_2026.orders.model.entity.Order;
import com.programandoenjava.bootcamp_1_2026.orders.model.projection.OrderDashboardView;
import com.programandoenjava.bootcamp_1_2026.orders.model.projection.OrderSummary;
import com.programandoenjava.bootcamp_1_2026.orders.model.service.output.OrderOutputDto;
import com.programandoenjava.bootcamp_1_2026.orders.model.service.output.OrderSummaryOutputDto;
import com.programandoenjava.bootcamp_1_2026.orders.model.service.output.OrderViewOutputDto;
import com.programandoenjava.bootcamp_1_2026.orders.repository.OrderRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;

    public OrderService(OrderRepository repository, OrderMapper converter) {
        this.repository = repository;
        this.mapper = converter;
    }

    public List<OrderOutputDto> getAllOrdersByProcessorName(String processorName) {
        try {
            List<Order> orderList = repository.findAllByProcessorNameIgnoreCase(processorName);
            return orderList.stream()
                    .map(this.mapper::entityToOutputDto)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new ApiException("OrderService.Order.getAll", "No se ha podido obtener la lista de pedidos");
        }
    }

    public List<OrderOutputDto> getAll() {
        try {
            List<Order> orderList = repository.findAll();
            return orderList.stream()
                    .map(this.mapper::entityToOutputDto)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new ApiException("OrderService.Order.getAll", "No se ha podido obtener la lista de pedidos");
        }
    }

    public List<OrderSummaryOutputDto> getSummary() {
        try {
            List<OrderSummary> orderList = repository.findOrderSummaryBy();
            return orderList.stream()
                    .map(this.mapper::projectionToOutputSummaryDto)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new ApiException("OrderService.Order.getSummary", "No se ha podido obtener el sumario de pedidos");
        }
    }

    public List<OrderOutputDto> searchWithFilters(RequestOrderFilter filter) {
        try {
            List<Order> orderList = repository
                    .findOrderByFilters(
                            filter.createdAtFrom(),
                            filter.createdAtTo(),
                            filter.totalAmountMin(),
                            filter.totalAmountMax(),
                            filter.productName());
            return orderList.stream()
                    .map(this.mapper::entityToOutputDto)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new ApiException("OrderService.Order.searchWithFilters", "No se ha podido obtener el listado de pedidos");
        }
    }

    public List<OrderViewOutputDto> getDashboardView() {
        try {
            List<OrderDashboardView> orderList = repository.findAllDashboard();
            return orderList.stream()
                    .map(this.mapper::viewToOutputViewDto)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new ApiException("OrderService.Order.getDashboardView", "No se ha podido obtener el listado del dashboard view");
        }
    }


}
