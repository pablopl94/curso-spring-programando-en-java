package com.programandoenjava.bootcamp_1_2026.order.utils;

import com.programandoenjava.bootcamp_1_2026.orderItem.model.application.OrderItemInputDto;

import java.util.Set;

public class OrderUtils {

    public static double calculateTotalPrice(Set<OrderItemInputDto> listaItems){
        double precioTotal = 0;
        //Calcular el precioTotal usando el precio real del producto desde BD
        for(OrderItemInputDto item: listaItems){
            precioTotal = precioTotal + ((item.getQuantity()) * (item.getUnitPrice()));
        }
        // Redondear a 2 decimales
        return  precioTotal = Math.round(precioTotal * 100.0) / 100.0;
    }

}
