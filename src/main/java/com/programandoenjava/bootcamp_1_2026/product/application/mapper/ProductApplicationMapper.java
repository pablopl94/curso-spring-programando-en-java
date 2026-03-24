package com.programandoenjava.bootcamp_1_2026.product.application.mapper;

import com.programandoenjava.bootcamp_1_2026.product.application.dto.in.ProductInput;
import com.programandoenjava.bootcamp_1_2026.product.application.dto.out.ProductOutput;
import com.programandoenjava.bootcamp_1_2026.product.domain.entity.Product;

public interface ProductApplicationMapper {

    default Product toDomain(ProductInput input) {
        if (input == null) return null;

        return new Product(
                null,
                input.name(),
                input.price(),
                input.stock()
        );
    }

    default Product updateDomain(Product product, ProductInput input) {
        if (input == null) return null;

        return new Product(
                product.id(),
                input.name(),
                input.price(),
                input.stock()
        );
    }

    default ProductOutput toOutput(Product product) {
        if (product == null) return null;

        return new ProductOutput(
                product.id(),
                product.name(),
                product.price(),
                product.stock()
        );
    }
}
