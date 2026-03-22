package com.programandoenjava.bootcamp_1_2026.product.service;

import com.programandoenjava.bootcamp_1_2026.product.exception.ProductNotFoundException;
import com.programandoenjava.bootcamp_1_2026.product.mapper.ProductMapper;
import com.programandoenjava.bootcamp_1_2026.product.model.application.ProductInputDto;
import com.programandoenjava.bootcamp_1_2026.product.model.application.ProductOutputDto;
import com.programandoenjava.bootcamp_1_2026.product.model.entity.Product;
import com.programandoenjava.bootcamp_1_2026.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper mapper;
    private final ProductRepository repository;

    public List<ProductOutputDto> getAll() {
        List<Product> listProducts = repository.findAll();
        return listProducts.stream()
                .map(mapper::entityToOutputDto)
                .collect(Collectors.toList());
    }

    public ProductOutputDto getById(Long id) {
        Product product = repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return mapper.entityToOutputDto(product);
    }

    public ProductOutputDto save(ProductInputDto product) {
        Product savedProduct = repository.save(mapper.inputToEntity(product));
        return mapper.entityToOutputDto(savedProduct);
    }

    public ProductOutputDto update(Long id, ProductInputDto product) {
        Product originalProduct = repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        Product updateProduct = mapper.updateEntity(originalProduct, product);
        repository.save(updateProduct);
        return mapper.entityToOutputDto(updateProduct);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ProductNotFoundException(id);
        }
    }

}
