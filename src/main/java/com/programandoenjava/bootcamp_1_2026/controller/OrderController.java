package com.programandoenjava.bootcamp_1_2026.controller;

import com.programandoenjava.bootcamp_1_2026.service.PaymentProcessor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final PaymentProcessor paymentProcessor;

    public OrderController(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    @PostMapping("/checkout")
    public void checkout (@RequestParam double amount ){

        paymentProcessor.process(amount);

    }
}
