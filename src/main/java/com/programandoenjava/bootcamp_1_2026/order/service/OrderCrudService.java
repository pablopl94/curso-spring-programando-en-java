package com.programandoenjava.bootcamp_1_2026.order.service;

import com.programandoenjava.bootcamp_1_2026.order.exception.OrderServiceException;
import com.programandoenjava.bootcamp_1_2026.order.mapper.OrderMapper;
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
class OrderCrudService {

    private final OrderRepository repository;
    private final OrderMapper mapper;

    public List<OrderOutputDto> getAll() {
        try {
            List<Order> orderList = repository.findAll();
            return orderList.stream()
                    .map(this.mapper::entityToOutputDto)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new OrderServiceException("OrderService.Order.getAll", "No se ha podido obtener la lista de pedidos");
        }
    }
}