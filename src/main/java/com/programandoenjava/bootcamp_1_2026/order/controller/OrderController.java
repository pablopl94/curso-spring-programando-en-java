package com.programandoenjava.bootcamp_1_2026.order.controller;

import com.programandoenjava.bootcamp_1_2026.order.mapper.OrderMapper;
import com.programandoenjava.bootcamp_1_2026.order.model.api.request.RequestOrderFilter;
import com.programandoenjava.bootcamp_1_2026.order.model.api.response.OrderDetailResponseDto;
import com.programandoenjava.bootcamp_1_2026.order.model.api.response.OrderResponseDto;
import com.programandoenjava.bootcamp_1_2026.order.model.application.output.OrderOutputDto;
import com.programandoenjava.bootcamp_1_2026.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;
    private final OrderMapper mapper;

    public OrderController(OrderService service, OrderMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping()
    public ResponseEntity<List<OrderResponseDto>> getAll(@RequestParam(required = false) String view) {
        List<OrderResponseDto> responseViewList = service.processView(view);
        return ResponseEntity.ok().body(responseViewList);
    }

    // Filtros con CriteriaBuilder para probar búsquedas avanzadas
    @GetMapping("/search")
    public ResponseEntity<List<OrderDetailResponseDto>> search(RequestOrderFilter filter) {
        List<OrderOutputDto> outputService = service.searchWithFilters(filter);
        List<OrderDetailResponseDto> response = outputService.stream()
                .map(this.mapper::outputToResponseDto)
                .toList();
        return ResponseEntity.ok().body(response);
    }

}

