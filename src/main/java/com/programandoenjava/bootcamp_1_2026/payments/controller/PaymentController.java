package com.programandoenjava.bootcamp_1_2026.payments.controller;


import com.programandoenjava.bootcamp_1_2026.payments.model.api.request.PaymentRequest;
import com.programandoenjava.bootcamp_1_2026.payments.model.api.response.PaymentResponse;
import com.programandoenjava.bootcamp_1_2026.payments.service.PaymentService;
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
