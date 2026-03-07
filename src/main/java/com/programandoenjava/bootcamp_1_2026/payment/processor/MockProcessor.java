package com.programandoenjava.bootcamp_1_2026.payment.processor;

import com.programandoenjava.bootcamp_1_2026.payment.exception.PaymentProcessorException;
import com.programandoenjava.bootcamp_1_2026.payment.model.api.PaymentRequest;
import com.programandoenjava.bootcamp_1_2026.payment.model.api.PaymentResponse;
import com.programandoenjava.bootcamp_1_2026.payment.model.constants.StatusPaymentEnum;
import com.programandoenjava.bootcamp_1_2026.payment.model.event.PaymentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Profile("dev")
public class MockProcessor extends AuditablePaymentProcessor {

    private static final Logger log = LoggerFactory.getLogger(MockProcessor.class);

    @Override
    public PaymentResponse process(PaymentRequest request) {
        if (request == null) throw new PaymentProcessorException("MockProcessor.Request", "La Request no puede ser nula");
        if (request.getAmount() < 0)
            throw new PaymentProcessorException("MockProcessor.Request.amount", "El monto no puede ser negativo");

        log.debug("Probando MockProcessor desde desarrollo ...");
        StatusPaymentEnum status = evalutedPayment(request.getAmount());

        if (status == null) throw new PaymentProcessorException("MockProcessor.Response.status", "El estado no puede ser nulo");
        if (status.equals(StatusPaymentEnum.ACCEPTED)) {
            getPublisher().publishEvent(new PaymentEvent(request.getAmount(), request.getUserEmail(), PaymentRequest.SUPPLIER_EMAIL));
        }

        log.debug("Procesando mensaje de respuesta ....");
        return new PaymentResponse(
                UUID.randomUUID().toString(),
                status,
                LocalDateTime.now(),
                request.getAmount()
        );
    }
}
