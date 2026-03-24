package com.programandoenjava.bootcamp_1_2026.product.domain.port.out;

import com.programandoenjava.bootcamp_1_2026.product.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(Long id);

    boolean existsById(Long id);

    List<Product> findAll();

    Product save(Product product);

    void deleteById(Long id);

    Long count();

    Optional<Product> findByIdWithLock(Long id);
}
