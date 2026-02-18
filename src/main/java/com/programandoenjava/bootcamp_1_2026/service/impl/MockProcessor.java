package com.programandoenjava.bootcamp_1_2026.service.impl;

import com.programandoenjava.bootcamp_1_2026.model.PaymentEvent;
import com.programandoenjava.bootcamp_1_2026.model.PaymentRequest;
import com.programandoenjava.bootcamp_1_2026.service.AuditablePaymentProcessor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class MockProcessor extends AuditablePaymentProcessor implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void process(PaymentRequest request) {
        System.out.println("Probando MockProcessor desde desarrollo ..." );
        publisher.publishEvent(new PaymentEvent(request.getAmount(),request.getUserEmail(),PaymentRequest.supplierEmail));
        // NOTA: Al ser asíncrono debería imprimir el siguiente System.out.println porque el hilo principal NO espera al listener
        System.out.println("Pago procesado por la cantidad de : " +  request.getAmount() + "€");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
