package com.programandoenjava.bootcamp_1_2026.order.service;

import com.programandoenjava.bootcamp_1_2026.order.mapper.CheckoutMapper;
import com.programandoenjava.bootcamp_1_2026.order.model.application.input.CheckoutInputDto;
import com.programandoenjava.bootcamp_1_2026.order.model.application.output.CheckoutOutputDto;
import com.programandoenjava.bootcamp_1_2026.orderItem.model.application.OrderItemInputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final CheckoutMapper mapper;

    public CheckoutOutputDto calculateCheckout(CheckoutInputDto checkoutDto, String email) {
        Double totalPrice = calculateTotalPrice(checkoutDto.getItems());
        checkoutDto.setTotalPrice(totalPrice);
        checkoutDto.setEmailCustomer(email);
        return mapper.inputToOutput(checkoutDto);
    }

    public static double calculateTotalPrice(Set<OrderItemInputDto> listaItems) {
        double precioTotal = 0;
        //Calcular el precioTotal usando el precio real del producto desde BD
        for (OrderItemInputDto item : listaItems) {
            precioTotal = precioTotal + ((item.getQuantity()) * (item.getUnitPrice()));
        }
        // Redondear a 2 decimales
        return precioTotal = Math.round(precioTotal * 100.0) / 100.0;
    }

}


