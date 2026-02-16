package com.programandoenjava.bootcamp_1_2026.service.impl;

import com.programandoenjava.bootcamp_1_2026.service.PaymentProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
@ConditionalOnProperty(name = "app.payment-provider", havingValue = "stripe")
public class StripeProcessor implements PaymentProcessor {

    @Value("app.stripeKey")
    private String stripeKey;

    @Override
    public void process(double amount) {
        System.out.println("Conectando a Stripe API mediante clave: " + stripeKey);
        System.out.println("Pago procesado por la cantidad de : " + amount + "€");
    }

}
