package com.programandoenjava.bootcamp_1_2026.product.infraestructure.config;

import com.programandoenjava.bootcamp_1_2026.product.application.service.ProductService;
import com.programandoenjava.bootcamp_1_2026.product.domain.port.out.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductInfrastructureConfig {

    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductService(productRepository);
    }
}
