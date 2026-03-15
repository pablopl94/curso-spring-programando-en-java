package com.programandoenjava.bootcamp_1_2026.product.service;

import com.programandoenjava.bootcamp_1_2026.product.mapper.ProductMapper;
import com.programandoenjava.bootcamp_1_2026.product.model.application.ProductOutputDto;
import com.programandoenjava.bootcamp_1_2026.product.model.entity.Product;
import com.programandoenjava.bootcamp_1_2026.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper mapper;
    private final ProductRepository repository;

    public List<ProductOutputDto> getAll() {
        List<Product> listProducts = repository.findAll();
        return listProducts.stream()
                .map(mapper::entityToOutputDto)
                .collect(Collectors.toList());
    }
}
