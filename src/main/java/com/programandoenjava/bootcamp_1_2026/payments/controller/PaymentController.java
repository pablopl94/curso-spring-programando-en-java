package com.programandoenjava.bootcamp_1_2026.payments.controller;


import com.programandoenjava.bootcamp_1_2026.payments.model.api.request.PaymentRequest;
import com.programandoenjava.bootcamp_1_2026.payments.model.api.response.PaymentResponse;
import com.programandoenjava.bootcamp_1_2026.payments.service.PaymentProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentProcessor paymentProcessor;

    public PaymentController(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    @PostMapping("/checkout")
    public ResponseEntity<PaymentResponse> checkout(@RequestBody PaymentRequest request) {
        PaymentResponse response = paymentProcessor.process(request);
        return ResponseEntity.ok().body(response);
    }

}
