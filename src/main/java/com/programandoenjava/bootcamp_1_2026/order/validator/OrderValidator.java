package com.programandoenjava.bootcamp_1_2026.order.validator;

import com.programandoenjava.bootcamp_1_2026.common.exceptions.ValidationException;
import com.programandoenjava.bootcamp_1_2026.common.validator.GenericValidator;
import com.programandoenjava.bootcamp_1_2026.order.model.application.input.OrderInputDto;
import com.programandoenjava.bootcamp_1_2026.product.exception.ProductNotFoundException;
import com.programandoenjava.bootcamp_1_2026.product.model.entity.Product;
import com.programandoenjava.bootcamp_1_2026.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderValidator implements GenericValidator<OrderInputDto> {

    private final ProductRepository productRepository;
    @Override
    public void validateInsert(OrderInputDto dto) {

        //Comprobar que queda stock de los productos
        dto.items().stream()
                .filter(item -> {
                    Product product = productRepository.findByIdWithLock(item.product().id())
                            .orElseThrow(()-> new ProductNotFoundException(item.product().id()));
                    return item.quantity() > product.getStock();
                })
                .findFirst()
                .ifPresent(item -> {
                    throw new ValidationException("La cantidad no puede ser mayor que el stock del producto");
                });

        //Comprobar que el precio del producto es igual al del unitPrice del orderItem
        dto.items().stream()
                .filter(item -> {
                     Product product = productRepository.findByIdWithLock(item.product().id())
                             .orElseThrow(()-> new ProductNotFoundException(item.product().id()));
                    return Math.abs(item.unitPrice() - product.getPrice()) > 0.001;
                })
                .findFirst()
                .ifPresent(item -> {
                    throw new ValidationException("El precio del producto ha cambiado");
                });
    }

    @Override
    public void validateUpdate(OrderInputDto dto) {

    }

    @Override
    public void validateDelete(OrderInputDto dto) {

    }
}
