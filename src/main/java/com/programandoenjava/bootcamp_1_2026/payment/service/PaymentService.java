package com.programandoenjava.bootcamp_1_2026.payment.service;

import com.programandoenjava.bootcamp_1_2026.config.application.JwtService;
import com.programandoenjava.bootcamp_1_2026.order.model.application.input.OrderInputDto;
import com.programandoenjava.bootcamp_1_2026.payment.exception.PaymentException;
import com.programandoenjava.bootcamp_1_2026.payment.model.api.PaymentRequestDto;
import com.programandoenjava.bootcamp_1_2026.payment.model.api.PaymentResponseDto;
import com.programandoenjava.bootcamp_1_2026.payment.processor.PaymentProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentProcessor paymentProcessor;
    private final JwtService jwtService;

    public PaymentResponseDto processPayment(PaymentRequestDto request,  Authentication authentication) {

        String email = jwtService.getClaimEmail(authentication);
        String name = jwtService.getClaimName(authentication);

        OrderInputDto inputOrder = OrderInputDto.builder()
                .customerEmail(email)
                .customerName(name)
                .items(request.items())
                .build();
        try {
            return paymentProcessor.process(request);
        } catch (Exception e) {
            throw new PaymentException("Fallo en el procesamiento");
        }
    }
}