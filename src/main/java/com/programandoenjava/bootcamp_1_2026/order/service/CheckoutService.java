package com.programandoenjava.bootcamp_1_2026.order.service;

import com.programandoenjava.bootcamp_1_2026.config.application.JwtService;
import com.programandoenjava.bootcamp_1_2026.order.model.api.request.ShoppingCartRequest;
import com.programandoenjava.bootcamp_1_2026.order.model.api.response.CheckoutResponseDto;
import com.programandoenjava.bootcamp_1_2026.orderItem.mapper.OrderItemMapper;
import com.programandoenjava.bootcamp_1_2026.orderItem.model.api.OrderItemRequestDto;
import com.programandoenjava.bootcamp_1_2026.orderItem.model.application.OrderItemInputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final OrderItemMapper orderItemMapper;
    private final JwtService jwtService;

    public CheckoutResponseDto calculateCheckout(ShoppingCartRequest request, Authentication authentication) {

        BigDecimal precioTotal = BigDecimal.ZERO;

        //Calcular el precioTotal usando el precio real del producto desde BD
        for(OrderItemRequestDto item: request.items()){
            // Mapear para obtener el producto completo con su precio
            OrderItemInputDto itemInput = orderItemMapper.requestToInputDto(item);
            BigDecimal itemPrice = BigDecimal.valueOf(itemInput.product().price());
            BigDecimal quantity = BigDecimal.valueOf(item.quantity());
            precioTotal = precioTotal.add(itemPrice.multiply(quantity));
        }

        // Redondear a 2 decimales
        double totalRedondeado = precioTotal.setScale(2, RoundingMode.HALF_UP).doubleValue();

        // Obtener email del JWT en Authentication
        String customerEmail = jwtService.getClaimEmail(authentication);

        return CheckoutResponseDto.builder()
                .totalAmount(totalRedondeado)
                .emailCustomer(customerEmail)
                .items(request.items())
                .build();
    }



}
