package com.programandoenjava.bootcamp_1_2026.order.application.service;

import com.programandoenjava.bootcamp_1_2026.order.domain.entity.Checkout;
import com.programandoenjava.bootcamp_1_2026.order.domain.port.in.usecase.CalculateCheckoutUseCase;

public class CheckoutService implements CalculateCheckoutUseCase {

    @Override
    public Checkout calculateCheckout(Checkout checkout, String email) {
        Double totalPrice = checkout.calculateTotalPrice();
        return Checkout.builder()
                .totalPrice(totalPrice)
                .emailCustomer(email)
                .items(checkout.items())
                .build();
    }
}
