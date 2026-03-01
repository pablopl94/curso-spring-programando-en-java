package com.programandoenjava.bootcamp_1_2026.payments.service.impl;

import com.programandoenjava.bootcamp_1_2026.exceptions.ApiException;
import com.programandoenjava.bootcamp_1_2026.payments.model.api.request.PaymentRequest;
import com.programandoenjava.bootcamp_1_2026.payments.model.api.response.PaymentResponse;
import com.programandoenjava.bootcamp_1_2026.payments.model.constants.StatusPaymentEnum;
import com.programandoenjava.bootcamp_1_2026.payments.model.event.PaymentEvent;
import com.programandoenjava.bootcamp_1_2026.payments.service.AuditablePaymentProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Profile("prod")
@ConditionalOnProperty(name = "app.payment-provider", havingValue = "paypal" )
public class PaypalProcessor extends AuditablePaymentProcessor{

    @Value("${app.paypal-key}")
    private String paypalKey;
    private static final Logger log = LoggerFactory.getLogger(PaypalProcessor.class);

    @Override
    public PaymentResponse process(PaymentRequest request) {
        if(request == null) throw new ApiException("Paypal.Request", "La request no puede ser nula");
        if(paypalKey == null || paypalKey.isBlank()) throw new ApiException("Paypal.PaypalKey", "La key de Paypal está vacía");

        log.debug("Conectando a Paypal API mediante clave: {}", paypalKey);
        StatusPaymentEnum status = evalutedPayment(request.getAmount());

        if (status == null) throw new ApiException("MockProcessor.Response.status", "El estado no puede ser nulo");
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
