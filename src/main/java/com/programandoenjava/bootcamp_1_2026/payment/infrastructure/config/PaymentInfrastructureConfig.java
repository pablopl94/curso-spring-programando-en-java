package com.programandoenjava.bootcamp_1_2026.payment.infrastructure.config;

import com.programandoenjava.bootcamp_1_2026.payment.application.service.PaymentService;
import com.programandoenjava.bootcamp_1_2026.payment.domain.port.out.OrderCreatorPort;
import com.programandoenjava.bootcamp_1_2026.payment.domain.port.out.PaymentEventPublisherPort;
import com.programandoenjava.bootcamp_1_2026.payment.domain.port.out.PaymentGatewayPort;
import com.programandoenjava.bootcamp_1_2026.payment.domain.port.out.ProductAvailabilityPort;
import com.programandoenjava.bootcamp_1_2026.payment.domain.port.out.UserLookupPort;
import com.programandoenjava.bootcamp_1_2026.payment.domain.service.PaymentStatusEvaluator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentInfrastructureConfig {

    @Bean
    public PaymentStatusEvaluator paymentStatusEvaluator() {
        return new PaymentStatusEvaluator();
    }

    @Bean
    public PaymentService paymentService(
            ProductAvailabilityPort productAvailability,
            UserLookupPort userLookup,
            PaymentGatewayPort gateway,
            OrderCreatorPort orderCreator,
            PaymentEventPublisherPort eventPublisher,
            @Value("${app.payment-provider}") String paymentProvider
    ) {
        return new PaymentService(productAvailability, userLookup, gateway, orderCreator, eventPublisher, paymentProvider);
    }
}
