package com.programandoenjava.bootcamp_1_2026.payment.domain.port.out;

import com.programandoenjava.bootcamp_1_2026.payment.domain.model.Payment;
import com.programandoenjava.bootcamp_1_2026.payment.domain.model.PaymentResult;

public interface PaymentGatewayPort {

    PaymentResult charge(Payment payment);
}
