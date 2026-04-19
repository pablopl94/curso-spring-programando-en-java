package com.programandoenjava.bootcamp_1_2026.order.infraestructure.api;

import com.programandoenjava.bootcamp_1_2026.order.application.service.OrderQueryService;
import com.programandoenjava.bootcamp_1_2026.order.application.service.OrderService;
import com.programandoenjava.bootcamp_1_2026.order.domain.entity.Order;
import com.programandoenjava.bootcamp_1_2026.order.domain.entity.OrderQuery;
import com.programandoenjava.bootcamp_1_2026.order.domain.entity.OrderView;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.api.dto.request.RequestOrderFilter;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.api.dto.response.OrderViewResponseDto;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.api.mapper.OrderApiMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderQueryService orderQueryService;
    private final OrderApiMapper orderApiMapper;

    public OrderController(
            OrderService orderService,
            OrderQueryService orderQueryService,
            OrderApiMapper orderApiMapper) {
        this.orderService = orderService;
        this.orderQueryService = orderQueryService;
        this.orderApiMapper = orderApiMapper;
    }

    @GetMapping
    public ResponseEntity<List<OrderViewResponseDto>> getAll(
            @RequestParam(required = false)
            @Pattern(regexp = "summary|dashboard", message = "La view debe ser 'summary' o 'dashboard'")
            String view,
            @Valid @ModelAttribute RequestOrderFilter filter) {

        OrderQuery query = new OrderQuery(view, orderApiMapper.toDomain(filter));
        List<? extends OrderView> result = orderQueryService.getOrders(query);
        List<OrderViewResponseDto> response = result.stream()
                .map(orderApiMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderViewResponseDto> getOne(
            @PathVariable @Positive(message = "El id debe ser positivo") Long id) {
        Order order = orderService.getOneOrder(id);
        return ResponseEntity.ok(orderApiMapper.toResponse(order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable @Positive(message = "El id debe ser positivo") Long id) {
        orderService.deleteOneOrder(id);
        return ResponseEntity.noContent().build();
    }
}
