package com.programandoenjava.bootcamp_1_2026.payment.model.application;

import com.programandoenjava.bootcamp_1_2026.payment.model.constants.StatusPaymentEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOutputDto {
    private String transactionId;
    private StatusPaymentEnum status;
    private LocalDateTime processedAt;
    private Double amount;

}