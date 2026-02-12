package com.programandoenjava.bootcamp_1_2026.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(
        name = ""
)
public interface PaymentProcessor {

    void process(double amount);
}
