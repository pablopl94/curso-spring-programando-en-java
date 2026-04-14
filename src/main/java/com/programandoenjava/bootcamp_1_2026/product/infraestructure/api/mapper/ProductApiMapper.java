package com.programandoenjava.bootcamp_1_2026.product.infraestructure.api.mapper;

import com.programandoenjava.bootcamp_1_2026.product.domain.entity.Product;
import com.programandoenjava.bootcamp_1_2026.product.infraestructure.api.dto.ProductRequest;
import com.programandoenjava.bootcamp_1_2026.product.infraestructure.api.dto.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public interface ProductApiMapper {

    default ProductResponse toResponse(Product domain) {
        if (domain == null) return null;

        return new ProductResponse(
                domain.id(),
                domain.name(),
                domain.price(),
                domain.stock()
        );
    }

    default Product toDomain(ProductRequest request) {
        if (request == null) return null;

        return new Product(
                null,
                request.name(),
                request.price(),
                request.stock()
        );
    }

}
