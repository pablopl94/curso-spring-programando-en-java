package com.programandoenjava.bootcamp_1_2026.payment.infrastructure.api;

import com.programandoenjava.bootcamp_1_2026.payment.domain.model.PaymentCommand;
import com.programandoenjava.bootcamp_1_2026.payment.domain.model.PaymentResult;
import com.programandoenjava.bootcamp_1_2026.payment.domain.port.in.ProcessPaymentUseCase;
import com.programandoenjava.bootcamp_1_2026.payment.infrastructure.api.dto.in.PaymentRequestDto;
import com.programandoenjava.bootcamp_1_2026.payment.infrastructure.api.dto.out.PaymentResponseDto;
import com.programandoenjava.bootcamp_1_2026.payment.infrastructure.api.mapper.PaymentApiMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/payments")
public class PaymentController {

    private final ProcessPaymentUseCase processPayment;
    private final PaymentApiMapper mapper;

    public PaymentController(ProcessPaymentUseCase processPayment, PaymentApiMapper mapper) {
        this.processPayment = processPayment;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDto> pay(
            @Valid @RequestBody PaymentRequestDto request,
            Authentication authentication) {

        PaymentCommand command = mapper.toCommand(request, authentication.getName());
        PaymentResult result = processPayment.process(command);
        return ResponseEntity.ok(mapper.toResponse(result));
    }
}
