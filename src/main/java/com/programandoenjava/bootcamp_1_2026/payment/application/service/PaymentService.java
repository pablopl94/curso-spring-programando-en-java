package com.programandoenjava.bootcamp_1_2026.payment.application.service;

import com.programandoenjava.bootcamp_1_2026.payment.domain.event.PaymentAcceptedEvent;
import com.programandoenjava.bootcamp_1_2026.payment.domain.model.Payment;
import com.programandoenjava.bootcamp_1_2026.payment.domain.model.PaymentCommand;
import com.programandoenjava.bootcamp_1_2026.payment.domain.model.PaymentResult;
import com.programandoenjava.bootcamp_1_2026.payment.domain.model.PaymentStatus;
import com.programandoenjava.bootcamp_1_2026.payment.domain.port.in.ProcessPaymentUseCase;
import com.programandoenjava.bootcamp_1_2026.payment.domain.port.out.OrderCreatorPort;
import com.programandoenjava.bootcamp_1_2026.payment.domain.port.out.PaymentEventPublisherPort;
import com.programandoenjava.bootcamp_1_2026.payment.domain.port.out.PaymentGatewayPort;
import com.programandoenjava.bootcamp_1_2026.payment.domain.port.out.ProductAvailabilityPort;
import com.programandoenjava.bootcamp_1_2026.payment.domain.port.out.UserLookupPort;
import com.programandoenjava.bootcamp_1_2026.user.domain.entity.User;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

public class PaymentService implements ProcessPaymentUseCase {

    private final ProductAvailabilityPort productAvailability;
    private final UserLookupPort userLookup;
    private final PaymentGatewayPort gateway;
    private final OrderCreatorPort orderCreator;
    private final PaymentEventPublisherPort eventPublisher;
    private final String paymentProvider;

    public PaymentService(
            ProductAvailabilityPort productAvailability,
            UserLookupPort userLookup,
            PaymentGatewayPort gateway,
            OrderCreatorPort orderCreator,
            PaymentEventPublisherPort eventPublisher,
            String paymentProvider
    ) {
        this.productAvailability = productAvailability;
        this.userLookup = userLookup;
        this.gateway = gateway;
        this.orderCreator = orderCreator;
        this.eventPublisher = eventPublisher;
        this.paymentProvider = paymentProvider;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public PaymentResult process(PaymentCommand command) {
        productAvailability.checkAvailability(command.items());

        User user = userLookup.findByEmail(command.customerEmail());

        Payment payment = new Payment(
                command.customerEmail(),
                command.totalAmount(),
                paymentProvider,
                command.items()
        );

        PaymentResult result = gateway.charge(payment);

        if (result.status() == PaymentStatus.ACCEPTED) {
            orderCreator.createOrder(payment, user);
            eventPublisher.publish(new PaymentAcceptedEvent(
                    payment.totalAmount(),
                    payment.customerEmail(),
                    payment.paymentProvider()
            ));
        }

        return result;
    }
}
