package com.programandoenjava.bootcamp_1_2026.order.service;

import com.programandoenjava.bootcamp_1_2026.order.exception.OrderServiceException;
import com.programandoenjava.bootcamp_1_2026.order.mapper.OrderMapper;
import com.programandoenjava.bootcamp_1_2026.order.model.api.request.RequestOrderFilter;
import com.programandoenjava.bootcamp_1_2026.order.model.application.output.OrderOutputDto;
import com.programandoenjava.bootcamp_1_2026.order.model.entity.Order;
import com.programandoenjava.bootcamp_1_2026.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class OrderFilterService {

    private final OrderRepository repository;
    private final OrderMapper mapper;

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
            throw new OrderServiceException("OrderService.Order.searchWithFilters", "No se ha podido obtener el listado de pedidos");
        }
    }
}