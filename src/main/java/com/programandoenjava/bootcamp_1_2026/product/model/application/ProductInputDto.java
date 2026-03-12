package com.programandoenjava.bootcamp_1_2026.product.model.application;

public record ProductInputDto(
        String name,
        Double price,
        Integer stock
) { }
