package com.programandoenjava.bootcamp_1_2026.service.impl;

import com.programandoenjava.bootcamp_1_2026.exception.PaymentException;
import com.programandoenjava.bootcamp_1_2026.model.PaymentEvent;
import com.programandoenjava.bootcamp_1_2026.model.PaymentRequest;
import com.programandoenjava.bootcamp_1_2026.service.AuditablePaymentProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
@ConditionalOnProperty(name = "app.payment-provider", havingValue = "stripe")
public class StripeProcessor extends AuditablePaymentProcessor {

    @Value("${app.stripe-key}")
    private String stripeKey;

    @Override
    public void process(PaymentRequest request) {
        if(request == null ) throw new PaymentException("PayPalProcessor.StripeKey", "La key está vacía");
        if(stripeKey == null || stripeKey.isBlank())  throw new PaymentException("StripeProcessor.Request", "La request es null");
        log.debug("Conectando a Stripe API mediante clave: {}", stripeKey);
        publisher.publishEvent(new PaymentEvent(request.getAmount(),request.getUserEmail(), PaymentRequest.supplierEmail));
        // NOTA: Al ser asíncrono debería imprimir el siguiente System.out.println porque el hilo principal NO espera al listener
        System.out.println("Pago procesado por la cantidad de: " + request.getAmount() + "€");
    }

}
