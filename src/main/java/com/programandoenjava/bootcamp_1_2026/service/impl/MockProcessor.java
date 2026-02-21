package com.programandoenjava.bootcamp_1_2026.service.impl;

import com.programandoenjava.bootcamp_1_2026.exception.PaymentException;
import com.programandoenjava.bootcamp_1_2026.model.PaymentEvent;
import com.programandoenjava.bootcamp_1_2026.model.PaymentRequest;
import com.programandoenjava.bootcamp_1_2026.service.AuditablePaymentProcessor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class MockProcessor extends AuditablePaymentProcessor {

    @Override
    public void process(PaymentRequest request) {
        if(request == null ) throw new PaymentException("MockProcessor.Request", "La Request no puede ser nula");
        log.debug("Probando MockProcessor desde desarrollo ..." );
        publisher.publishEvent(new PaymentEvent(request.getAmount(),request.getUserEmail(),PaymentRequest.supplierEmail));
        // NOTA: Al ser asíncrono debería imprimir el siguiente System.out.println porque el hilo principal NO espera al listener
        log.debug("Pago procesado por la cantidad de : {}€", request.getAmount());
    }

}
