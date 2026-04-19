package com.programandoenjava.bootcamp_1_2026.payment.infrastructure.adapter.gateway;

import com.programandoenjava.bootcamp_1_2026.payment.domain.exception.PaymentProcessorException;
import com.programandoenjava.bootcamp_1_2026.payment.domain.model.Payment;
import com.programandoenjava.bootcamp_1_2026.payment.domain.model.PaymentResult;
import com.programandoenjava.bootcamp_1_2026.payment.domain.model.PaymentStatus;
import com.programandoenjava.bootcamp_1_2026.payment.domain.port.out.PaymentGatewayPort;
import com.programandoenjava.bootcamp_1_2026.payment.domain.service.PaymentStatusEvaluator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Profile("prod")
@ConditionalOnProperty(name = "app.payment-provider", havingValue = "paypal")
public class PaypalGatewayAdapter implements PaymentGatewayPort {

    private static final Logger log = LoggerFactory.getLogger(PaypalGatewayAdapter.class);

    private final PaymentStatusEvaluator statusEvaluator;

    @Value("${app.paypal-key}")
    private String paypalKey;

    public PaypalGatewayAdapter(PaymentStatusEvaluator statusEvaluator) {
        this.statusEvaluator = statusEvaluator;
    }

    @Override
    public PaymentResult charge(Payment payment) {
        if (paypalKey == null || paypalKey.isBlank()) {
            throw new PaymentProcessorException("Paypal.Key", "La key de Paypal está vacía");
        }
        log.debug("[Paypal] Conectando a Paypal API con clave: {}", paypalKey);

        PaymentStatus status = statusEvaluator.evaluate(payment.totalAmount());

        return new PaymentResult(
                UUID.randomUUID().toString(),
                status,
                LocalDateTime.now(),
                payment.totalAmount()
        );
    }
}
