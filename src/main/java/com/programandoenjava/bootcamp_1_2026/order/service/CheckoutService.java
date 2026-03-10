package com.programandoenjava.bootcamp_1_2026.order.service;

import com.programandoenjava.bootcamp_1_2026.order.mapper.CheckoutMapper;
import com.programandoenjava.bootcamp_1_2026.order.model.application.output.CheckoutOutputDto;
import com.programandoenjava.bootcamp_1_2026.order.model.application.input.CheckoutInputDto;
import com.programandoenjava.bootcamp_1_2026.order.utils.OrderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final CheckoutMapper mapper;

    public CheckoutOutputDto calculateCheckout(CheckoutInputDto checkoutDto, String email) {
        double totalPrice = OrderUtils.calculateTotalPrice(checkoutDto.getItems());
        checkoutDto.setTotalPrice(totalPrice);
        checkoutDto.setEmailCustomer(email);
        return mapper.inputToOutput(checkoutDto);
    }


}


