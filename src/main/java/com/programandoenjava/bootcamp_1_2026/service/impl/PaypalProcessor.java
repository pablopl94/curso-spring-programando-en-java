package com.programandoenjava.bootcamp_1_2026.service.impl;

import com.programandoenjava.bootcamp_1_2026.exception.PaymentException;
import com.programandoenjava.bootcamp_1_2026.model.PaymentEvent;
import com.programandoenjava.bootcamp_1_2026.model.PaymentRequest;
import com.programandoenjava.bootcamp_1_2026.service.AuditablePaymentProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
@ConditionalOnProperty(name = "app.payment-provider", havingValue = "paypal" )
public class PaypalProcessor extends AuditablePaymentProcessor implements ApplicationEventPublisherAware {

    @Value("${app.paypal-key}")
    private String paypalKey;

    @Override
    public void process(PaymentRequest request) {
        if(request == null ) throw new PaymentException("Paypal.PaypalKey", "La key está vacía");
        if(paypalKey == null || paypalKey.isBlank())  throw new PaymentException("Paypal.Request", "La request es null");
        log.debug("Conectando a Paypal API mediante clave: {}", paypalKey);
        publisher.publishEvent(new PaymentEvent(request.getAmount(),request.getUserEmail(),PaymentRequest.supplierEmail));
        // NOTA: Al ser asíncrono debería imprimir el siguiente System.out.println porque el hilo principal NO espera al listener
        log.debug("Pago procesado por la cantidad de : {}€", request.getAmount());
    }


}
