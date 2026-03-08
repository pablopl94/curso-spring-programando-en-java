package com.programandoenjava.bootcamp_1_2026.order.controller;

import com.programandoenjava.bootcamp_1_2026.order.mapper.OrderMapper;
import com.programandoenjava.bootcamp_1_2026.order.model.api.request.RequestOrderFilter;
import com.programandoenjava.bootcamp_1_2026.order.model.api.request.ShoppingCartRequest;
import com.programandoenjava.bootcamp_1_2026.order.model.api.response.CheckoutResponseDto;
import com.programandoenjava.bootcamp_1_2026.order.model.api.response.OrderResponseDto;
import com.programandoenjava.bootcamp_1_2026.order.model.api.response.OrderSummaryResponseDto;
import com.programandoenjava.bootcamp_1_2026.order.model.api.response.OrderViewResponseDto;
import com.programandoenjava.bootcamp_1_2026.order.model.application.output.OrderOutputDto;
import com.programandoenjava.bootcamp_1_2026.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<?>> getAll(@RequestParam(required = false) String view) {

        // Usa proyecciones para traer solo algunos campos
        if (view != null && view.equals("summary")) {
            List<OrderOutputDto> outputService = service.getSummary();
            List<OrderSummaryResponseDto> response = outputService.stream()
                    .map(this.mapper::outputSummaryToSummaryResponseDto)
                    .toList();
            return ResponseEntity.ok().body(response);
        }

        // Usa Blaze EntityViews con agregaciones
        if (view != null && view.equals("dashboard")) {
            List<OrderViewResponseDto> response = service.getDashboardView();
            return ResponseEntity.ok().body(response);
        }

        // Obtiene todas las orders usando @EntityGraph
        List<OrderOutputDto> outputService = service.getAll();
        List<OrderResponseDto> response = outputService.stream()
                .map(this.mapper::outputToResponseDto)
                .toList();
        return ResponseEntity.ok().body(response);
    }

    // Filtros con CriteriaBuilder para probar búsquedas avanzadas
    @GetMapping("/search")
    public ResponseEntity<List<OrderResponseDto>> search(RequestOrderFilter filter) {
        List<OrderOutputDto> outputService = service.searchWithFilters(filter);
        List<OrderResponseDto> response = outputService.stream()
                .map(this.mapper::outputToResponseDto)
                .toList();
        return ResponseEntity.ok().body(response);
    }

    // Emula el  checkout en base a un shopping ficticio
    @PostMapping("/checkout")
    public ResponseEntity<CheckoutResponseDto> checkout(@RequestBody ShoppingCartRequest request, Authentication authentication){
        return ResponseEntity.ok().body(service.calculateCheckout(request,authentication));
    }
}

