package com.programandoenjava.bootcamp_1_2026.product.infraestructure.database;

import com.programandoenjava.bootcamp_1_2026.product.domain.entity.Product;
import com.programandoenjava.bootcamp_1_2026.product.domain.port.out.ProductRepository;
import com.programandoenjava.bootcamp_1_2026.product.infraestructure.database.entity.ProductEntity;
import com.programandoenjava.bootcamp_1_2026.product.infraestructure.database.mapper.ProductRepositoryMapper;
import com.programandoenjava.bootcamp_1_2026.product.infraestructure.database.repository.QueryProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final QueryProductRepository queryProductRepository;
    private final ProductRepositoryMapper productRepositoryMapper;

    public ProductRepositoryImpl(
            QueryProductRepository queryProductRepository,
            ProductRepositoryMapper productRepositoryMapper) {
        this.queryProductRepository = queryProductRepository;
        this.productRepositoryMapper = productRepositoryMapper;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return queryProductRepository.findById(id).map(productRepositoryMapper::toDomain);
    }

    @Override
    public boolean existsById(Long id) {
        return queryProductRepository.existsById(id);
    }

    @Override
    public List<Product> findAll() {
        return queryProductRepository.findAll().stream().map(productRepositoryMapper::toDomain).toList();
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = productRepositoryMapper.toEntity(product);
        ProductEntity savedEntity = queryProductRepository.save(productEntity);
        return productRepositoryMapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        queryProductRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return queryProductRepository.count();
    }
    
    @Override
    public Optional<Product> findByIdWithLock(Long id) {
        return queryProductRepository.findByIdWithLock(id).map(productRepositoryMapper::toDomain);
    }
}
