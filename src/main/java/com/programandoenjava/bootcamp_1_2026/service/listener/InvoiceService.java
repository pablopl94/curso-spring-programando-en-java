package com.programandoenjava.bootcamp_1_2026.service.listener;

import com.programandoenjava.bootcamp_1_2026.model.PaymentEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class InvoiceService {

    @Async
    @EventListener
    public void sendInvoiceEvent(PaymentEvent event){
        System.out.println("PROCESANDO FACTURA -->");
        System.out.println("Correo de usuario al que enviar la factura: " + event.userEmail());
        System.out.println("Generando factura proforma para el pago de: " + event.monto() +"€");
        System.out.println("Email del proveedor: " + event.supplierEmail());
    }
}
