package com.programandoenjava.bootcamp_1_2026.controller;


import com.programandoenjava.bootcamp_1_2026.model.PaymentRequest;
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
    public ResponseEntity<PaymentRequest> checkout (@RequestBody PaymentRequest request) {
        paymentProcessor.process(request);
        return ResponseEntity.noContent().build();
    }
}
