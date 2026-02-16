package com.programandoenjava.bootcamp_1_2026.controller;

import com.programandoenjava.bootcamp_1_2026.model.AmountRequest;
import com.programandoenjava.bootcamp_1_2026.service.PaymentProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final PaymentProcessor paymentProcessor;

    public OrderController(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    @PostMapping("/checkout")
    public ResponseEntity<AmountRequest> checkout (@RequestBody AmountRequest amount) {
        paymentProcessor.process(amount.getAmount());
        return ResponseEntity.noContent().build();
    }
}
