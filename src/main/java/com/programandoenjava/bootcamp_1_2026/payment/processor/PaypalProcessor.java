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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.payment-provider", havingValue = "paypal" )
public class PaypalProcessor extends AuditablePaymentProcessor{

    @Value("${app.paypal-key}")
    private String paypalKey;
    private static final Logger log = LoggerFactory.getLogger(PaypalProcessor.class);
    private final PaymentMapper mapper;

    @Override
    public PaymentOutputDto process(PaymentInputDto request) {
        if(request == null) throw new PaymentProcessorException("Paypal.Request", "La request no puede ser nula");
        if(paypalKey == null || paypalKey.isBlank()) throw new PaymentProcessorException("Paypal.PaypalKey", "La key de Paypal está vacía");

        log.debug("Conectando a Paypal API mediante clave: {}", paypalKey);
        StatusPaymentEnum status = evalutedPayment(request.getTotalAmount());

        if (status == null) throw new PaymentProcessorException("PaypalProcessor.Response.status", "El estado no puede ser nulo");
        if (status.equals(StatusPaymentEnum.ACCEPTED)) {
            getPublisher().publishEvent(new PaymentEvent(request.getTotalAmount(), request.getCustomerEmail(), request.getPaymentProvider()));
        }

        log.debug("Procesando mensaje de respuesta ....");
        return mapper.inputToOutput(request);
    }
}
