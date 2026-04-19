package com.programandoenjava.bootcamp_1_2026.order.domain.port.in.usecase;

import com.programandoenjava.bootcamp_1_2026.order.domain.entity.Checkout;

public interface CalculateCheckoutUseCase {

    Checkout calculateCheckout(Checkout checkout, String email);
}
