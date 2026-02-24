package com.programandoenjava.bootcamp_1_2026.service;

import com.programandoenjava.bootcamp_1_2026.model.PaymentRequest;
import com.programandoenjava.bootcamp_1_2026.model.PaymentResponse;
import com.programandoenjava.bootcamp_1_2026.model.StatusPaymentEnum;

public interface PaymentProcessor{

    //Método de proceso de pago
    PaymentResponse process(PaymentRequest request);

    //Método simulación evaluación del pago
    //Lo añado al interface por si en algún caso alguna plataforma necesita añadir validaciones
    //Por defecto dejo unas validaciones básicas
    default StatusPaymentEnum evalutedPayment(double amount) {
        if (amount >= 50_000) return StatusPaymentEnum.DETECTED_FRAUD;
        if (amount >= 5_000) return StatusPaymentEnum.REQUIRES_VERIFICATION;
        return StatusPaymentEnum.ACCEPTED;
    }

}
