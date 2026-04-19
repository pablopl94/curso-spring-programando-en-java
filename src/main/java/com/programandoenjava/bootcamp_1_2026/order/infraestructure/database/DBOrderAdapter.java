package com.programandoenjava.bootcamp_1_2026.order.infraestructure.database;

import com.programandoenjava.bootcamp_1_2026.order.domain.entity.Order;
import com.programandoenjava.bootcamp_1_2026.order.domain.entity.OrderFilter;
import com.programandoenjava.bootcamp_1_2026.order.domain.port.out.OrderRepository;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.database.mapper.OrderRepositoryMapper;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.database.model.entity.OrderEntity;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.database.model.entity.OrderEntity_;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.database.repository.SpringJpaOrderRepository;
import com.programandoenjava.bootcamp_1_2026.orderItem.infrastructure.database.entity.OrderItemEntity;
import com.programandoenjava.bootcamp_1_2026.orderItem.infrastructure.database.entity.OrderItemEntity_;
import com.programandoenjava.bootcamp_1_2026.product.infraestructure.database.entity.ProductEntity;
import com.programandoenjava.bootcamp_1_2026.product.infraestructure.database.entity.ProductEntity_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DBOrderAdapter implements OrderRepository {

    private final EntityManager em;
    private final SpringJpaOrderRepository springJpaOrderRepository;
    private final OrderRepositoryMapper orderRepositoryMapper;

    public DBOrderAdapter(EntityManager em, SpringJpaOrderRepository springJpaOrderRepository, OrderRepositoryMapper orderRepositoryMapper) {
        this.em = em;
        this.springJpaOrderRepository = springJpaOrderRepository;
        this.orderRepositoryMapper = orderRepositoryMapper;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return springJpaOrderRepository.findById(id).map(orderRepositoryMapper::toDomain);
    }

    @Override
    public List<Order> findAll() {
        return springJpaOrderRepository.findAll().stream()
                .map(orderRepositoryMapper::toDomain)
                .toList();
    }

    @Override
    public Order save(Order order) {
        OrderEntity entity = orderRepositoryMapper.toEntity(order);
        OrderEntity saved = springJpaOrderRepository.save(entity);
        return orderRepositoryMapper.toDomain(saved);
    }

    @Override
    public Boolean existsById(Long id) {
        return springJpaOrderRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        springJpaOrderRepository.deleteById(id);
    }

    @Override
    public List<Order> search(OrderFilter filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OrderEntity> cq = cb.createQuery(OrderEntity.class);
        Root<OrderEntity> order = cq.from(OrderEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        if (filter.createdAtFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(order.get(OrderEntity_.createdAt), filter.createdAtFrom()));
        }

        if (filter.createdAtTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(order.get(OrderEntity_.createdAt), filter.createdAtTo()));
        }

        if (filter.totalAmountMin() != null) {
            predicates.add(cb.greaterThanOrEqualTo(order.get(OrderEntity_.totalAmount), filter.totalAmountMin()));
        }

        if (filter.totalAmountMax() != null) {
            predicates.add(cb.lessThanOrEqualTo(order.get(OrderEntity_.totalAmount), filter.totalAmountMax()));
        }

        if (filter.productName() != null && !filter.productName().isBlank()) {
            Join<OrderEntity, OrderItemEntity> itemJoin = order.join(OrderEntity_.items, JoinType.LEFT);
            Join<OrderItemEntity, ProductEntity> productJoin = itemJoin.join(OrderItemEntity_.product, JoinType.LEFT);
            predicates.add(cb.like(productJoin.get(ProductEntity_.name), "%" + filter.productName() + "%"));
        }

        order.fetch(OrderEntity_.items, JoinType.LEFT).fetch(OrderItemEntity_.product, JoinType.LEFT);

        cq.select(order).distinct(true).where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList().stream()
                .map(orderRepositoryMapper::toDomain)
                .toList();
    }
}
