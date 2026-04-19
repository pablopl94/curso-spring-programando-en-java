package com.programandoenjava.bootcamp_1_2026.order.infraestructure.database;

import com.programandoenjava.bootcamp_1_2026.order.domain.entity.OrderSummary;
import com.programandoenjava.bootcamp_1_2026.order.domain.port.out.OrderSummaryRepository;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.database.mapper.OrderSummaryRepositoryMapper;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.database.repository.SpringJpaOrderSummaryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DBOrderSummaryAdapter implements OrderSummaryRepository {

    private final SpringJpaOrderSummaryRepository springJpaOrderSummaryRepository;
    private final OrderSummaryRepositoryMapper orderSummaryRepositoryMapper;

    public DBOrderSummaryAdapter(SpringJpaOrderSummaryRepository springJpaOrderSummaryRepository,
                                 OrderSummaryRepositoryMapper orderSummaryRepositoryMapper) {
        this.springJpaOrderSummaryRepository = springJpaOrderSummaryRepository;
        this.orderSummaryRepositoryMapper = orderSummaryRepositoryMapper;
    }

    @Override
    public List<OrderSummary> findAllByOrderSummary() {
        return springJpaOrderSummaryRepository.findAllByOrderSummary().stream()
                .map(orderSummaryRepositoryMapper::toDomain)
                .toList();
    }
}
