package com.programandoenjava.bootcamp_1_2026.product.infraestructure.api.mapper;

import com.programandoenjava.bootcamp_1_2026.product.application.dto.in.ProductInput;
import com.programandoenjava.bootcamp_1_2026.product.application.dto.out.ProductOutput;
import com.programandoenjava.bootcamp_1_2026.product.infraestructure.api.dto.ProductRequest;
import com.programandoenjava.bootcamp_1_2026.product.infraestructure.api.dto.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public interface ProductApiMapper {

    default ProductResponse toResponse(ProductOutput output) {
        if (output == null) return null;

        return new ProductResponse(
                output.id(),
                output.name(),
                output.price(),
                output.stock()
        );
    }

    default ProductInput toInput(ProductRequest request) {
        if (request == null) return null;

        return new ProductInput(
                request.name(),
                request.price(),
                request.stock()
        );
    }

}
