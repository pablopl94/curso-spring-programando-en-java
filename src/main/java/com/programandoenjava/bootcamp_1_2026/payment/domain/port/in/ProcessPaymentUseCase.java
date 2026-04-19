package com.programandoenjava.bootcamp_1_2026.payment.domain.port.in;

import com.programandoenjava.bootcamp_1_2026.payment.domain.model.PaymentCommand;
import com.programandoenjava.bootcamp_1_2026.payment.domain.model.PaymentResult;

public interface ProcessPaymentUseCase {

    PaymentResult process(PaymentCommand command);
}
