package com.programandoenjava.bootcamp_1_2026.payment.service;

import com.programandoenjava.bootcamp_1_2026.payment.model.event.PaymentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class InvoiceService {

    private static final Logger log = LoggerFactory.getLogger(InvoiceService.class);
    @Async
    @EventListener
    public void sendInvoiceEvent(PaymentEvent event){
        log.info("PROCESANDO FACTURA -->");
        log.info("Correo de usuario al que enviar la factura: {}", event.userEmail());
        log.info("Generando factura proforma para el pago de: {}€", event.monto());
        log.info("Email del proveedor: {}", event.supplierEmail());
    }
}
