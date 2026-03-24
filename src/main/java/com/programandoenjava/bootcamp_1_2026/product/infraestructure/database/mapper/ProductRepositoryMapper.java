package com.programandoenjava.bootcamp_1_2026.product.infraestructure.database.mapper;

import com.programandoenjava.bootcamp_1_2026.product.domain.entity.Product;
import com.programandoenjava.bootcamp_1_2026.product.infraestructure.database.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public interface ProductRepositoryMapper {

    default Product toDomain(ProductEntity productEntity) {
        if (productEntity == null) return null;

        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getPrice(),
                productEntity.getStock()
        );
    }

    default ProductEntity toEntity(Product product) {
        if (product == null) return null;

        return new ProductEntity(
                product.id(),
                product.name(),
                product.price(),
                product.stock()
        );
    }

}
