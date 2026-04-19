package com.programandoenjava.bootcamp_1_2026.payment.infrastructure.adapter;

import com.programandoenjava.bootcamp_1_2026.payment.domain.exception.PriceChangedException;
import com.programandoenjava.bootcamp_1_2026.payment.domain.exception.StockInsufficientException;
import com.programandoenjava.bootcamp_1_2026.payment.domain.model.PaymentItem;
import com.programandoenjava.bootcamp_1_2026.payment.domain.port.out.ProductAvailabilityPort;
import com.programandoenjava.bootcamp_1_2026.product.domain.entity.Product;
import com.programandoenjava.bootcamp_1_2026.product.domain.exception.ProductNotFoundException;
import com.programandoenjava.bootcamp_1_2026.product.domain.port.out.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ProductAvailabilityAdapter implements ProductAvailabilityPort {

    private static final double PRICE_TOLERANCE = 0.001;

    private final ProductRepository productRepository;

    public ProductAvailabilityAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void checkAvailability(Set<PaymentItem> items) {
        for (PaymentItem item : items) {
            Product product = productRepository.findByIdWithLock(item.productId())
                    .orElseThrow(() -> new ProductNotFoundException(item.productId()));

            if (item.quantity() > product.stock()) {
                throw new StockInsufficientException(item.productId());
            }
            if (Math.abs(item.unitPrice() - product.price()) > PRICE_TOLERANCE) {
                throw new PriceChangedException(item.productId());
            }
        }
    }
}
