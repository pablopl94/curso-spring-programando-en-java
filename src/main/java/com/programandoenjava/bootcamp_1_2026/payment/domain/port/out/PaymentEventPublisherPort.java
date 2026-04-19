package com.programandoenjava.bootcamp_1_2026.payment.domain.port.out;

import com.programandoenjava.bootcamp_1_2026.payment.domain.event.PaymentAcceptedEvent;

public interface PaymentEventPublisherPort {

    void publish(PaymentAcceptedEvent event);
}
