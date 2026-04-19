package com.programandoenjava.bootcamp_1_2026.payment.domain.port.out;

import com.programandoenjava.bootcamp_1_2026.payment.domain.model.PaymentItem;

import java.util.Set;

public interface ProductAvailabilityPort {

    void checkAvailability(Set<PaymentItem> items);
}
