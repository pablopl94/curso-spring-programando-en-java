package com.programandoenjava.bootcamp_1_2026.payment.service;

import com.programandoenjava.bootcamp_1_2026.order.service.OrderService;
import com.programandoenjava.bootcamp_1_2026.payment.model.application.PaymentInputDto;
import com.programandoenjava.bootcamp_1_2026.payment.model.application.PaymentOutputDto;
import com.programandoenjava.bootcamp_1_2026.payment.processor.PaymentProcessor;
import com.programandoenjava.bootcamp_1_2026.payment.validator.PaymentValidator;
import com.programandoenjava.bootcamp_1_2026.user.model.application.output.UserOutputDto;
import com.programandoenjava.bootcamp_1_2026.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentProcessor paymentProcessor;
    private final UserService usuarioService;
    private final OrderService orderService;
    private final PaymentValidator validator;

    @Value("app.payment-provider")
    private String PAYMENT_PROVIDER;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public PaymentOutputDto processPayment(PaymentInputDto paymentInput, String username) {
        //Validamos que los productos tengan suficiente stock y que no haya cambiado su precio
        //En esta clase están los methods del repo con @Lock
        validator.validateInsert(paymentInput);
        //Añadimos el payment provider al paymentInput y el userName
        paymentInput.setPaymentProvider(PAYMENT_PROVIDER);
        //Buscamos al usuario con el email
        UserOutputDto user = usuarioService.getUserByEmail(username);
        //Creamos la orden a partir el usuario y el request
        orderService.createOrder(paymentInput, user);

        //Procesamos el pedido en el proveedor de pago activo
        return paymentProcessor.process(paymentInput);
    }
}