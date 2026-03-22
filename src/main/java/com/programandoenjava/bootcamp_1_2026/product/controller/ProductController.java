package com.programandoenjava.bootcamp_1_2026.product.controller;

import com.programandoenjava.bootcamp_1_2026.product.mapper.ProductMapper;
import com.programandoenjava.bootcamp_1_2026.product.model.api.ProductResponseDto;
import com.programandoenjava.bootcamp_1_2026.product.model.application.ProductInputDto;
import com.programandoenjava.bootcamp_1_2026.product.model.application.ProductOutputDto;
import com.programandoenjava.bootcamp_1_2026.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getOne(@PathVariable Long id) {
        ProductOutputDto output = service.getById(id);
        ProductResponseDto response = mapper.outputToResponseDto(output);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody ProductInputDto product) {
        ProductOutputDto response = service.save(product);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(
            @PathVariable Long id,
            @RequestBody ProductInputDto product) {
        ProductOutputDto updateProduct = service.update(id, product);
        ProductResponseDto response = mapper.outputToResponseDto(updateProduct);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

