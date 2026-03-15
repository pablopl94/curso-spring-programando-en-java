package com.programandoenjava.bootcamp_1_2026.payment;

import com.programandoenjava.bootcamp_1_2026.order.model.application.output.OrderOutputDto;
import com.programandoenjava.bootcamp_1_2026.order.service.OrderService;
import com.programandoenjava.bootcamp_1_2026.payment.model.application.PaymentInputDto;
import com.programandoenjava.bootcamp_1_2026.payment.model.application.PaymentOutputDto;
import com.programandoenjava.bootcamp_1_2026.payment.processor.PaymentProcessor;
import com.programandoenjava.bootcamp_1_2026.payment.service.PaymentService;
import com.programandoenjava.bootcamp_1_2026.payment.validator.PaymentValidator;
import com.programandoenjava.bootcamp_1_2026.user.model.application.output.UserOutputDto;
import com.programandoenjava.bootcamp_1_2026.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class ProcessPaymentTest {

    @Mock
    PaymentValidator validator;

    @Mock
    UserService usuarioService;

    @Mock
    OrderService orderService;

    @Mock
    PaymentProcessor paymentProcessor;

    @InjectMocks
    PaymentService paymentService;

    @Test
    public void test01_createdOrderCalle_whenProcessIsSuccessful() {

        // Arrange
        Double totalAmount = 10.00;
        String email = "pablo@test.com";
        UserOutputDto user = new UserOutputDto();
        OrderOutputDto order = new OrderOutputDto();
        PaymentOutputDto paymentResponse = new PaymentOutputDto();
        PaymentInputDto paymentInput = PaymentInputDto.builder()
                .paymentProvider("paypal")
                .customerEmail(email)
                .totalAmount(totalAmount)
                .build();
        given(usuarioService.getUserByEmail(email)).willReturn(user);
        given(paymentProcessor.process(paymentInput)).willReturn(paymentResponse);

        // Act
        paymentService.processPayment(paymentInput, email);

        //Asserts
        verify(orderService, times(1)).createOrder(paymentInput, user);
    }

}
