package com.programandoenjava.bootcamp_1_2026.payment.controller;


import com.programandoenjava.bootcamp_1_2026.payment.mapper.PaymentMapper;
import com.programandoenjava.bootcamp_1_2026.payment.model.api.PaymentRequestDto;
import com.programandoenjava.bootcamp_1_2026.payment.model.api.PaymentResponseDto;
import com.programandoenjava.bootcamp_1_2026.payment.model.application.PaymentInputDto;
import com.programandoenjava.bootcamp_1_2026.payment.model.application.PaymentOutputDto;
import com.programandoenjava.bootcamp_1_2026.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService service;
    private final PaymentMapper mapper;

    @PostMapping()
    public ResponseEntity<PaymentResponseDto> pay(@RequestBody PaymentRequestDto request, Authentication authentication) {
        String username = authentication.getName();
        PaymentInputDto paymentInput = mapper.requestToInputDto(request);
        PaymentOutputDto paymentOutput = service.processPayment(paymentInput, username);
        PaymentResponseDto response = mapper.outputToResponseDto(paymentOutput);
        return ResponseEntity.ok().body(response);
    }

}
