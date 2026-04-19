package com.programandoenjava.bootcamp_1_2026.payment.domain.port.out;

import com.programandoenjava.bootcamp_1_2026.payment.domain.model.Payment;
import com.programandoenjava.bootcamp_1_2026.user.domain.entity.User;

public interface OrderCreatorPort {

    void createOrder(Payment payment, User user);
}
