package com.programandoenjava.bootcamp_1_2026.product.infraestructure.config;

import com.programandoenjava.bootcamp_1_2026.product.application.mapper.ProductApplicationMapper;
import com.programandoenjava.bootcamp_1_2026.product.application.service.ProductService;
import com.programandoenjava.bootcamp_1_2026.product.domain.port.out.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    public ProductService productService(
            ProductRepository productRepository,
            ProductApplicationMapper productApplicationMapper) {
        return new ProductService(productRepository, productApplicationMapper);
    }
}
