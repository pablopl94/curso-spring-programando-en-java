package com.programandoenjava.bootcamp_1_2026.payment.infrastructure.adapter;

import com.programandoenjava.bootcamp_1_2026.payment.domain.event.PaymentAcceptedEvent;
import com.programandoenjava.bootcamp_1_2026.payment.domain.port.out.PaymentEventPublisherPort;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SpringEventPublisherAdapter implements PaymentEventPublisherPort {

    private final ApplicationEventPublisher publisher;

    public SpringEventPublisherAdapter(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(PaymentAcceptedEvent event) {
        publisher.publishEvent(event);
    }
}
