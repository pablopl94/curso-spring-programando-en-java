package com.programandoenjava.bootcamp_1_2026.payment.processor;

import com.programandoenjava.bootcamp_1_2026.payment.model.api.PaymentRequestDto;
import com.programandoenjava.bootcamp_1_2026.payment.model.api.PaymentResponseDto;
import com.programandoenjava.bootcamp_1_2026.payment.model.constants.StatusPaymentEnum;

public interface PaymentProcessor{

    //Método de proceso de pago
    PaymentResponseDto process(PaymentRequestDto request);

    //Método simulación evaluación del pago
    //Lo añado al interface por si en algún caso alguna plataforma necesita añadir validaciones
    //Por defecto dejo unas validaciones básicas
    default StatusPaymentEnum evalutedPayment(double amount) {
        if (amount >= 50_000) return StatusPaymentEnum.DETECTED_FRAUD;
        if (amount >= 5_000) return StatusPaymentEnum.REQUIRES_VERIFICATION;
        return StatusPaymentEnum.ACCEPTED;
    }

}
