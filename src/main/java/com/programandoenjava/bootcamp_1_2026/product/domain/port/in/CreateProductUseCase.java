package com.programandoenjava.bootcamp_1_2026.product.domain.port.in;

import com.programandoenjava.bootcamp_1_2026.product.domain.entity.Product;

public interface CreateProductUseCase {

    Product createProduct(Product product);
}
