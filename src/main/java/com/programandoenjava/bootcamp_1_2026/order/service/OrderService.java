package com.programandoenjava.bootcamp_1_2026.order.service;

import com.programandoenjava.bootcamp_1_2026.order.exception.OrderServiceException;
import com.programandoenjava.bootcamp_1_2026.order.mapper.OrderMapper;
import com.programandoenjava.bootcamp_1_2026.order.model.api.request.RequestOrderFilter;
import com.programandoenjava.bootcamp_1_2026.order.model.api.response.OrderResponseDto;
import com.programandoenjava.bootcamp_1_2026.order.model.api.response.OrderSummaryResponseDto;
import com.programandoenjava.bootcamp_1_2026.order.model.application.input.OrderInputDto;
import com.programandoenjava.bootcamp_1_2026.order.model.application.output.OrderOutputDto;
import com.programandoenjava.bootcamp_1_2026.order.model.entity.Order;
import com.programandoenjava.bootcamp_1_2026.order.repository.OrderRepository;
import com.programandoenjava.bootcamp_1_2026.order.repository.impl.OrderDashboardView;
import com.programandoenjava.bootcamp_1_2026.order.repository.projection.OrderSummary;
import com.programandoenjava.bootcamp_1_2026.payment.model.application.PaymentInputDto;
import com.programandoenjava.bootcamp_1_2026.user.model.application.output.UserOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;

    public List<OrderResponseDto> processView(String view){
        // Usa proyecciones para traer solo algunos campos
        if (view != null && view.equals("summary")) {
            return getSummary().stream()
                    .map(this.mapper::outputToSummaryResponseDto)
                    .collect(Collectors.toList());
        }
        // Usa Blaze EntityViews con agregaciones
        if (view != null && view.equals("dashboard")) {
            return getDashboardView().stream()
                    .map(this.mapper::outPutToViewResponseDto)
                    .map(OrderSummaryResponseDto.class::cast)
                    .collect(Collectors.toList());
        }
        // Obtiene todas las orders usando @EntityGraph
         return getAll().stream()
                 .map(this.mapper::outputToResponseDto)
                 .collect(Collectors.toList());
    }

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

    public List<OrderOutputDto> getSummary() {
        try {
            List<OrderSummary> orderList = repository.findOrderSummaryBy();
            return orderList.stream()
                    .map(this.mapper::projectionToOutputDto)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new OrderServiceException("OrderService.Order.getSummary", "No se ha podido obtener el sumario de pedidos");
        }
    }

    public List<OrderOutputDto> getDashboardView() {
        try {
            List<OrderDashboardView> orderList = repository.findAllDashboard();
            return orderList.stream()
                    .map(this.mapper::viewToOutputDto)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new OrderServiceException("OrderService.Order.getDashboardView", "No se ha podido obtener el listado del dashboard view");
        }
    }

    public OrderOutputDto createOrder(PaymentInputDto payment, UserOutputDto user){
        OrderInputDto newOrderInput = OrderInputDto.builder()
                .customerEmail(user.getEmail())
                .processorName(user.getName())
                .processorName(payment.getPaymentProvider())
                .items(payment.getItems())
                .createdAt(LocalDateTime.now())
                .build();

        Order newOrder = mapper.inputToEntity(newOrderInput);
        repository.save(newOrder);
        return mapper.entityToOutputDto(newOrder);
    }


}
