package com.programandoenjava.bootcamp_1_2026.orders.controller;

import com.programandoenjava.bootcamp_1_2026.orders.mapper.OrderMapper;
import com.programandoenjava.bootcamp_1_2026.orders.model.api.response.OrderResponseDto;
import com.programandoenjava.bootcamp_1_2026.orders.model.api.response.OrderSummaryResponseDto;
import com.programandoenjava.bootcamp_1_2026.orders.model.api.response.OrderViewResponseDto;
import com.programandoenjava.bootcamp_1_2026.orders.model.service.output.OrderOutputDto;
import com.programandoenjava.bootcamp_1_2026.orders.model.service.output.OrderSummaryOutputDto;
import com.programandoenjava.bootcamp_1_2026.orders.model.service.output.OrderViewOutputDto;
import com.programandoenjava.bootcamp_1_2026.orders.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("")
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        List<OrderOutputDto> outputService = service.getAll();
        List<OrderResponseDto> response = outputService.stream()
                .map(this.mapper::outputToResponseDto)
                .toList();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/processor/{processorName}")
    public ResponseEntity<List<OrderResponseDto>> getAllByProcessorName(@PathVariable String processorName) {
        List<OrderOutputDto> outputService = service.getAllOrdersByProcessorName(processorName);
        List<OrderResponseDto> response = outputService.stream()
                .map(this.mapper::outputToResponseDto)
                .toList();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/summary")
    public ResponseEntity<List<OrderSummaryResponseDto>> getSummary() {
        List<OrderSummaryOutputDto> outputService = service.getSummary();
        List<OrderSummaryResponseDto> response = outputService.stream()
                .map(this.mapper::outputSummaryToSummaryResponseDto)
                .toList();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<OrderResponseDto>> search(
            @RequestParam(required = false) LocalDate createdAtFrom,
            @RequestParam(required = false) LocalDate createdAtTo,
            @RequestParam(required = false) Double totalAmountMin,
            @RequestParam(required = false) Double totalAmountMax,
            @RequestParam(required = false) String productName) {
        List<OrderOutputDto> outputService = service.searchWithFilters(createdAtFrom, createdAtTo, totalAmountMin, totalAmountMax, productName);
        List<OrderResponseDto> response = outputService.stream()
                .map(this.mapper::outputToResponseDto)
                .toList();
        return ResponseEntity.ok().body(response);
    }

//    @GetMapping("/view")
//    public ResponseEntity<List<OrderViewResponseDto>> getView() {
//        List<OrderViewOutputDto> outputService = service.getView();
//        List<OrderViewResponseDto> response = outputService.stream()
//                .map(this.mapper::outputViewToViewResponseDto)
//                .toList();
//        return ResponseEntity.ok().body(response);
//    }

}
