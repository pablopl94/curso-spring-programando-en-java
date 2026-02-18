package com.programandoenjava.bootcamp_1_2026.service.impl;

import com.programandoenjava.bootcamp_1_2026.model.PaymentEvent;
import com.programandoenjava.bootcamp_1_2026.model.PaymentRequest;
import com.programandoenjava.bootcamp_1_2026.service.PaymentProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
@ConditionalOnProperty(name = "app.payment-provider", havingValue = "stripe")
public class StripeProcessor implements PaymentProcessor, ApplicationEventPublisherAware {

    @Value("${app.stripe-key}")
    private String stripeKey;
    private ApplicationEventPublisher publisher;

    @Override
    public void process(PaymentRequest request) {
        System.out.println("Conectando a Stripe API mediante clave: " + stripeKey);
        publisher.publishEvent(new PaymentEvent(request.getAmount(),request.getUserEmail(), PaymentRequest.supplierEmail));
        // NOTA: Al ser asíncrono debería imprimir el siguiente System.out.println porque el hilo principal NO espera al listener
        System.out.println("Pago procesado por la cantidad de: " + request.getAmount() + "€");
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
