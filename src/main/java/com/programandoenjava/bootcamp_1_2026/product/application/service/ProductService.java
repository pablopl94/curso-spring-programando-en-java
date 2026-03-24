package com.programandoenjava.bootcamp_1_2026.product.application.service;

import com.programandoenjava.bootcamp_1_2026.product.application.dto.in.ProductInput;
import com.programandoenjava.bootcamp_1_2026.product.application.dto.out.ProductOutput;
import com.programandoenjava.bootcamp_1_2026.product.application.mapper.ProductApplicationMapper;
import com.programandoenjava.bootcamp_1_2026.product.domain.entity.Product;
import com.programandoenjava.bootcamp_1_2026.product.domain.exception.ProductNotFoundException;
import com.programandoenjava.bootcamp_1_2026.product.domain.port.in.CreateProductUseCase;
import com.programandoenjava.bootcamp_1_2026.product.domain.port.in.GetProductUseCase;
import com.programandoenjava.bootcamp_1_2026.product.domain.port.in.DeleteProductUseCase;
import com.programandoenjava.bootcamp_1_2026.product.domain.port.out.ProductRepository;

import java.util.List;

public class ProductService implements CreateProductUseCase, GetProductUseCase, DeleteProductUseCase {

    private final ProductRepository productRepository;
    private final ProductApplicationMapper productApplicationMapper;

    public ProductService(ProductRepository productRepository, ProductApplicationMapper productApplicationMapper) {
        this.productRepository = productRepository;
        this.productApplicationMapper = productApplicationMapper;
    }

    @Override
    public List<ProductOutput> getAllProducts() {
        return productRepository.findAll().stream().map(productApplicationMapper::toOutput).toList();
    }

    @Override
    public ProductOutput getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return productApplicationMapper.toOutput(product);
    }

    @Override
    public ProductOutput createProduct(ProductInput input) {
        Product product = productApplicationMapper.toDomain(input);
        Product savedProduct = productRepository.save(product);
        return productApplicationMapper.toOutput(savedProduct);
    }

    @Override
    public ProductOutput updateProduct(Long id, ProductInput product) {
        Product originalProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        Product updateProduct = productApplicationMapper.updateDomain(originalProduct, product);
        Product savedProduct = productRepository.save(updateProduct);
        return productApplicationMapper.toOutput(savedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException(id);
        }
    }

}
