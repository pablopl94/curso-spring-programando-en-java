package com.programandoenjava.bootcamp_1_2026.product.domain.port.in;

import com.programandoenjava.bootcamp_1_2026.product.domain.entity.Product;

import java.util.List;

public interface GetAllProductsUseCase {

    List<Product> getAllProducts();
}
