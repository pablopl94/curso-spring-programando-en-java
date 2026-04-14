package com.programandoenjava.bootcamp_1_2026.product.application.service;

import com.programandoenjava.bootcamp_1_2026.product.domain.entity.Product;
import com.programandoenjava.bootcamp_1_2026.product.domain.exception.ProductNotFoundException;
import com.programandoenjava.bootcamp_1_2026.product.domain.port.in.*;
import com.programandoenjava.bootcamp_1_2026.product.domain.port.out.ProductRepository;
import org.springframework.dao.DataAccessException;

import java.util.List;

public class ProductService implements CreateProductUseCase, UpdateProductUseCase, GetProductUseCase, GetAllProductsUseCase, DeleteProductUseCase {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getOneProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }


    @Override
    public Product updateProduct(Product product, Long id) {
        Product originalProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        Product newProduct = Product.builder()
                .id(originalProduct.id())
                .name(product.name())
                .stock(product.stock())
                .price(product.price())
                .build();
        return productRepository.save(newProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        try {
            productRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new RuntimeException(STR."Error técnico al eliminar el producto con id: \{id}", e);
        }
    }

}
