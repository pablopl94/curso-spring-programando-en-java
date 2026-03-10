package com.programandoenjava.bootcamp_1_2026.payment.processor;

import com.programandoenjava.bootcamp_1_2026.payment.model.application.PaymentInputDto;
import com.programandoenjava.bootcamp_1_2026.payment.model.application.PaymentOutputDto;
import com.programandoenjava.bootcamp_1_2026.payment.model.constants.StatusPaymentEnum;

public interface PaymentProcessor{

    //Proceso de pago
    PaymentOutputDto process(PaymentInputDto request);

    //Simulación evaluación del pago
    //Lo añado al interface por si en algún caso alguna plataforma necesita añadir validaciones
    //Por defecto dejo unas validaciones básicas
    default StatusPaymentEnum evalutedPayment(double amount) {
        if (amount >= 50_000) return StatusPaymentEnum.DETECTED_FRAUD;
        if (amount >= 5_000) return StatusPaymentEnum.REQUIRES_VERIFICATION;
        return StatusPaymentEnum.ACCEPTED;
    }

}
