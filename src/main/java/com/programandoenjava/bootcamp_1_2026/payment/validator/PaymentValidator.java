package com.programandoenjava.bootcamp_1_2026.payment.validator;

import com.programandoenjava.bootcamp_1_2026.common.exceptions.ValidationException;
import com.programandoenjava.bootcamp_1_2026.common.validator.GenericValidator;
import com.programandoenjava.bootcamp_1_2026.payment.model.application.PaymentInputDto;
import com.programandoenjava.bootcamp_1_2026.product.exception.ProductNotFoundException;
import com.programandoenjava.bootcamp_1_2026.product.model.entity.Product;
import com.programandoenjava.bootcamp_1_2026.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentValidator implements GenericValidator<PaymentInputDto> {

    private final ProductRepository productRepository;

    @Override
    public void validateInsert(PaymentInputDto dto) {
        validateStockAvailability(dto);
        validateProductPrices(dto);
        validateTotalAmount(dto);
    }

    @Override
    public void validateUpdate(PaymentInputDto dto) {
    }

    @Override
    public void validateDelete(PaymentInputDto dto) {

    }

    private void validateStockAvailability(PaymentInputDto dto) {
        dto.getItems().stream()
                .filter(item -> {
                    Product product = productRepository.findByIdWithLock(item.getIdProduct())
                            .orElseThrow(() -> new ProductNotFoundException(item.getIdProduct()));
                    return item.getQuantity() > product.getStock();
                })
                .findFirst()
                .ifPresent(item -> {
                    throw new ValidationException("La cantidad no puede ser mayor que el stock del producto");
                });
    }

    private void validateProductPrices(PaymentInputDto dto) {
        dto.getItems().stream()
                .filter(item -> {
                    Product product = productRepository.findByIdWithLock(item.getIdProduct())
                            .orElseThrow(() -> new ProductNotFoundException(item.getIdProduct()));
                    return Math.abs(item.getUnitPrice() - product.getPrice()) > 0.001;
                })
                .findFirst()
                .ifPresent(item -> {
                    throw new ValidationException("El precio del producto ha cambiado");
                });
    }

    private void validateTotalAmount(PaymentInputDto dto) {
        if (dto.getTotalAmount() <= 0) {
            throw new ValidationException("El precio total no puede ser negativo o cero");
        }
    }

}