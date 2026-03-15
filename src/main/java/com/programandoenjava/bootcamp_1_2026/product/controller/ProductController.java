package com.programandoenjava.bootcamp_1_2026.product.controller;

import com.programandoenjava.bootcamp_1_2026.product.mapper.ProductMapper;
import com.programandoenjava.bootcamp_1_2026.product.model.api.ProductResponseDto;
import com.programandoenjava.bootcamp_1_2026.product.model.application.ProductOutputDto;
import com.programandoenjava.bootcamp_1_2026.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;
    private final ProductMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAll() {
        List<ProductOutputDto> output = service.getAll();
        List<ProductResponseDto> response = output.stream()
                .map(mapper::outputToResponseDto)
                .toList();
        return ResponseEntity.ok().body(response);
    }

}

