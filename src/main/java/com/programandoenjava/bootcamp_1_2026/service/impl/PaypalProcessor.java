package com.programandoenjava.bootcamp_1_2026.service.impl;

import com.programandoenjava.bootcamp_1_2026.service.PaymentProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
@ConditionalOnProperty(name = "app.payment-provider", havingValue = "paypal" )
public class PaypalProcessor implements PaymentProcessor {

    @Value("app.paypal-key")
    private String paypalKey;

    @Override
    public void process(double amount) {
        System.out.println("Conectando a Paypal API mediante clave: " + paypalKey);
        System.out.println("Pago procesado por la cantidad de : " + amount + "€");
    }
}
