package com.programandoenjava.bootcamp_1_2026.payment.infrastructure.api.mapper;

import com.programandoenjava.bootcamp_1_2026.payment.domain.model.PaymentCommand;
import com.programandoenjava.bootcamp_1_2026.payment.domain.model.PaymentItem;
import com.programandoenjava.bootcamp_1_2026.payment.domain.model.PaymentResult;
import com.programandoenjava.bootcamp_1_2026.payment.infrastructure.api.dto.in.PaymentItemRequestDto;
import com.programandoenjava.bootcamp_1_2026.payment.infrastructure.api.dto.in.PaymentRequestDto;
import com.programandoenjava.bootcamp_1_2026.payment.infrastructure.api.dto.out.PaymentResponseDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PaymentApiMapper {

    public PaymentCommand toCommand(PaymentRequestDto request, String customerEmail) {
        Set<PaymentItem> items = request.items().stream()
                .map(this::toItem)
                .collect(Collectors.toSet());
        return new PaymentCommand(customerEmail, request.totalAmount(), items);
    }

    public PaymentResponseDto toResponse(PaymentResult result) {
        return new PaymentResponseDto(
                result.transactionId(),
                result.status(),
                result.processedAt(),
                result.amount()
        );
    }

    private PaymentItem toItem(PaymentItemRequestDto dto) {
        return new PaymentItem(dto.idProduct(), dto.quantity(), dto.unitPrice());
    }
}
