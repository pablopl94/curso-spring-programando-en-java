package com.programandoenjava.bootcamp_1_2026.payment.infrastructure.adapter.gateway;

import com.programandoenjava.bootcamp_1_2026.payment.domain.exception.PaymentProcessorException;
import com.programandoenjava.bootcamp_1_2026.payment.domain.model.Payment;
import com.programandoenjava.bootcamp_1_2026.payment.domain.model.PaymentResult;
import com.programandoenjava.bootcamp_1_2026.payment.domain.model.PaymentStatus;
import com.programandoenjava.bootcamp_1_2026.payment.domain.port.out.PaymentGatewayPort;
import com.programandoenjava.bootcamp_1_2026.payment.domain.service.PaymentStatusEvaluator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Profile({"dev", "test"})
public class MockGatewayAdapter implements PaymentGatewayPort {

    private static final Logger log = LoggerFactory.getLogger(MockGatewayAdapter.class);

    private final PaymentStatusEvaluator statusEvaluator;

    public MockGatewayAdapter(PaymentStatusEvaluator statusEvaluator) {
        this.statusEvaluator = statusEvaluator;
    }

    @Override
    public PaymentResult charge(Payment payment) {
        if (payment.totalAmount() < 0) {
            throw new PaymentProcessorException("MockGateway.Amount", "El monto no puede ser negativo");
        }
        log.debug("[MockGateway] Procesando pago de {} para {}", payment.totalAmount(), payment.customerEmail());

        PaymentStatus status = statusEvaluator.evaluate(payment.totalAmount());

        return new PaymentResult(
                UUID.randomUUID().toString(),
                status,
                LocalDateTime.now(),
                payment.totalAmount()
        );
    }
}
