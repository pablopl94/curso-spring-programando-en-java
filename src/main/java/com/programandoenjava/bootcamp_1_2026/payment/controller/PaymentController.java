package com.programandoenjava.bootcamp_1_2026.payment.controller;


import com.programandoenjava.bootcamp_1_2026.payment.model.api.PaymentRequest;
import com.programandoenjava.bootcamp_1_2026.payment.model.api.PaymentResponse;
import com.programandoenjava.bootcamp_1_2026.payment.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<PaymentResponse> checkout(@RequestBody PaymentRequest request) {
        PaymentResponse response = paymentService.processPayment(request);
        return ResponseEntity.ok().body(response);
    }

}
