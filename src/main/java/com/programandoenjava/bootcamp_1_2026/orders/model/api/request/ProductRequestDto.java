package com.programandoenjava.bootcamp_1_2026.orders.model.api.request;

public record ProductRequestDto(
    String name,
    Double price
) { }