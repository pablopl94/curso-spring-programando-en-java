package com.programandoenjava.bootcamp_1_2026.payment.infrastructure.adapter;

import com.programandoenjava.bootcamp_1_2026.order.domain.entity.Order;
import com.programandoenjava.bootcamp_1_2026.order.domain.port.in.usecase.CreateOrderUseCase;
import com.programandoenjava.bootcamp_1_2026.orderItem.domain.entity.OrderItem;
import com.programandoenjava.bootcamp_1_2026.payment.domain.model.Payment;
import com.programandoenjava.bootcamp_1_2026.payment.domain.model.PaymentItem;
import com.programandoenjava.bootcamp_1_2026.payment.domain.port.out.OrderCreatorPort;
import com.programandoenjava.bootcamp_1_2026.product.domain.entity.Product;
import com.programandoenjava.bootcamp_1_2026.product.domain.exception.ProductNotFoundException;
import com.programandoenjava.bootcamp_1_2026.product.domain.port.out.ProductRepository;
import com.programandoenjava.bootcamp_1_2026.user.domain.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderCreatorAdapter implements OrderCreatorPort {

    private final CreateOrderUseCase createOrderUseCase;
    private final ProductRepository productRepository;

    public OrderCreatorAdapter(CreateOrderUseCase createOrderUseCase, ProductRepository productRepository) {
        this.createOrderUseCase = createOrderUseCase;
        this.productRepository = productRepository;
    }

    @Override
    public void createOrder(Payment payment, User user) {
        Set<OrderItem> items = payment.items().stream()
                .map(this::toOrderItem)
                .collect(Collectors.toSet());

        Order order = Order.builder()
                .totalAmount(payment.totalAmount())
                .processorName(payment.paymentProvider())
                .customerName(user.name())
                .customerEmail(user.email())
                .items(items)
                .createdAt(LocalDateTime.now())
                .build();

        createOrderUseCase.createOrder(order, user);
    }

    private OrderItem toOrderItem(PaymentItem item) {
        Product product = productRepository.findById(item.productId())
                .orElseThrow(() -> new ProductNotFoundException(item.productId()));
        return new OrderItem(null, item.quantity(), item.unitPrice(), product, 0L);
    }
}
