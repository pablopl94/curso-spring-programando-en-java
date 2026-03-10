package com.programandoenjava.bootcamp_1_2026.payment.processor;

import com.programandoenjava.bootcamp_1_2026.payment.exception.PaymentProcessorException;
import com.programandoenjava.bootcamp_1_2026.payment.mapper.PaymentMapper;
import com.programandoenjava.bootcamp_1_2026.payment.model.application.PaymentInputDto;
import com.programandoenjava.bootcamp_1_2026.payment.model.application.PaymentOutputDto;
import com.programandoenjava.bootcamp_1_2026.payment.model.constants.StatusPaymentEnum;
import com.programandoenjava.bootcamp_1_2026.payment.model.event.PaymentEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("dev")
public class MockProcessor extends AuditablePaymentProcessor {

    private static final Logger log = LoggerFactory.getLogger(MockProcessor.class);
    private final PaymentMapper mapper;

    @Override
    public PaymentOutputDto process(PaymentInputDto request) {
        if (request == null) throw new PaymentProcessorException("MockProcessor.Request", "La Request no puede ser nula");
        if (request.getTotalAmount() < 0)
            throw new PaymentProcessorException("MockProcessor.Request.amount", "El monto no puede ser negativo");

        log.debug("Probando MockProcessor desde desarrollo ...");
        StatusPaymentEnum status = evalutedPayment(request.getTotalAmount());

        if (status == null) throw new PaymentProcessorException("MockProcessor.Response.status", "El estado no puede ser nulo");
        if (status.equals(StatusPaymentEnum.ACCEPTED)) {
            getPublisher().publishEvent(new PaymentEvent(request.getTotalAmount(), request.getCustomerEmail(), request.getPaymentProvider()));
        }

        log.debug("Procesando mensaje de respuesta ....");
        return mapper.inputToOutput(request);
    }
}
