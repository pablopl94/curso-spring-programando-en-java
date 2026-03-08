package com.programandoenjava.bootcamp_1_2026.payment.controller;


import com.programandoenjava.bootcamp_1_2026.payment.service.PaymentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

//    @PostMapping()
//    public ResponseEntity<PaymentResponseDto> pay(@RequestBody ShoppingCartDto shoppingCart) {
//
//    }

}
