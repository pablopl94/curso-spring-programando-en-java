package com.programandoenjava.bootcamp_1_2026.product.repository;

import com.programandoenjava.bootcamp_1_2026.product.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
