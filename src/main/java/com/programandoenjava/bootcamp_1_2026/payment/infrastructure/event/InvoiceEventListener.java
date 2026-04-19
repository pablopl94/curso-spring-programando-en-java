package com.programandoenjava.bootcamp_1_2026.payment.infrastructure.event;

import com.programandoenjava.bootcamp_1_2026.payment.domain.event.PaymentAcceptedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class InvoiceEventListener {

    private static final Logger log = LoggerFactory.getLogger(InvoiceEventListener.class);

    @Async
    @EventListener
    public void onPaymentAccepted(PaymentAcceptedEvent event) {
        log.info("PROCESANDO FACTURA -->");
        log.info("Correo del cliente: {}", event.customerEmail());
        log.info("Generando factura proforma para el pago de: {}€", event.amount());
        log.info("Proveedor de pago: {}", event.paymentProvider());
    }
}
