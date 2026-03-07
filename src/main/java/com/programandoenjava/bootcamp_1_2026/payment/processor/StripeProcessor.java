package com.programandoenjava.bootcamp_1_2026.payment.processor;

import com.programandoenjava.bootcamp_1_2026.payment.exception.PaymentProcessorException;
import com.programandoenjava.bootcamp_1_2026.payment.model.api.PaymentRequest;
import com.programandoenjava.bootcamp_1_2026.payment.model.api.PaymentResponse;
import com.programandoenjava.bootcamp_1_2026.payment.model.constants.StatusPaymentEnum;
import com.programandoenjava.bootcamp_1_2026.payment.model.event.PaymentEvent;
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
@ConditionalOnProperty(name = "app.payment-provider", havingValue = "stripe")
public class StripeProcessor extends AuditablePaymentProcessor {

    @Value("${app.stripe-key}")
    private String stripeKey;
    private static final Logger log = LoggerFactory.getLogger(StripeProcessor.class);

    @Override
    public PaymentResponse process(PaymentRequest request) {
        if(request == null ) throw new PaymentProcessorException("StripeProcessor.Request", "La request es null");
        if(stripeKey == null || stripeKey.isBlank())  throw new PaymentProcessorException("StripeProcessor.StripeKey", "La key está vacía");
        log.debug("Conectando a Stripe API mediante clave: {}", stripeKey);
        StatusPaymentEnum status = evalutedPayment(request.getAmount());

        if (status == null) throw new PaymentProcessorException("StripeProcessor.Response.status", "El estado no puede ser nulo");
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
