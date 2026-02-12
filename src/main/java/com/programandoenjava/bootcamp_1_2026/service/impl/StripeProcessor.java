package com.programandoenjava.bootcamp_1_2026.service.impl;

import com.programandoenjava.bootcamp_1_2026.service.PaymentProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
@ConditionalOnProperty(name = "app.payment-provider", havingValue = "stripe")
public class StripeProcessor implements PaymentProcessor {

    private final Environment env;
    public StripeProcessor(Environment env) {
        this.env = env;
    }

    @Override
    public void process(double amount) {
        System.out.println("Conectando a Stripe API mediante clave: " + env.getProperty("app.stripe-provider"));
        System.out.println("Pago procesado por la cantidad de : " + amount + "€");
    }
}
