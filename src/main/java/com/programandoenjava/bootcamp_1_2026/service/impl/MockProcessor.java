package com.programandoenjava.bootcamp_1_2026.service.impl;

import com.programandoenjava.bootcamp_1_2026.service.PaymentProcessor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("default, dev")
public class MockProcessor implements PaymentProcessor{

    @Override
    public void process(double amount) {

        System.out.println("Probando MockProcessor desde desarrollo ..." );
        System.out.println("Pago procesado por la cantidad de : " + amount + "€");

    }
}
