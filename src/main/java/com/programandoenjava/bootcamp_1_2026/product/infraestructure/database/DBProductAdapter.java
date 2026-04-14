package com.programandoenjava.bootcamp_1_2026.product.infraestructure.database;

import com.programandoenjava.bootcamp_1_2026.product.domain.entity.Product;
import com.programandoenjava.bootcamp_1_2026.product.domain.port.out.ProductRepository;
import com.programandoenjava.bootcamp_1_2026.product.infraestructure.database.entity.ProductEntity;
import com.programandoenjava.bootcamp_1_2026.product.infraestructure.database.mapper.ProductRepositoryMapper;
import com.programandoenjava.bootcamp_1_2026.product.infraestructure.database.repository.SpringJPAProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DBProductAdapter implements ProductRepository {

    private final SpringJPAProductRepository springJpaProductRepository;
    private final ProductRepositoryMapper productRepositoryMapper;

    public DBProductAdapter(SpringJPAProductRepository springJpaProductRepository, ProductRepositoryMapper productRepositoryMapper) {
        this.springJpaProductRepository = springJpaProductRepository;
        this.productRepositoryMapper = productRepositoryMapper;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return springJpaProductRepository.findById(id).map(productRepositoryMapper::toDomain);
    }

    @Override
    public boolean existsById(Long id) {
        return springJpaProductRepository.existsById(id);
    }

    @Override
    public List<Product> findAll() {
        return springJpaProductRepository.findAll().stream().map(productRepositoryMapper::toDomain).toList();
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = productRepositoryMapper.toEntity(product);
        ProductEntity savedEntity = springJpaProductRepository.save(productEntity);
        return productRepositoryMapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        springJpaProductRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return springJpaProductRepository.count();
    }

    @Override
    public Optional<Product> findByIdWithLock(Long id) {
        return springJpaProductRepository.findByIdWithLock(id).map(productRepositoryMapper::toDomain);
    }
}
