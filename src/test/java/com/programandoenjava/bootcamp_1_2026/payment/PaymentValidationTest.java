package com.programandoenjava.bootcamp_1_2026.payment;

import com.programandoenjava.bootcamp_1_2026.common.exceptions.ValidationException;
import com.programandoenjava.bootcamp_1_2026.orderItem.model.application.OrderItemInputDto;
import com.programandoenjava.bootcamp_1_2026.payment.model.application.PaymentInputDto;
import com.programandoenjava.bootcamp_1_2026.payment.validator.PaymentValidator;
import com.programandoenjava.bootcamp_1_2026.product.model.entity.Product;
import com.programandoenjava.bootcamp_1_2026.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class PaymentValidationTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    PaymentValidator paymentValidator;

    @Test
    void test01_expectValidationException_whenStockIsInsufficient() {

        // Arrange
        Product product = Product.builder()
                .id(1L)
                .stock(5)
                .price(50.0)
                .build();

        PaymentInputDto paymentInput = PaymentInputDto.builder()
                .items(Set.of(OrderItemInputDto.builder()
                        .idProduct(1L)
                        .quantity(10)
                        .unitPrice(50.0)
                        .build()))
                .build();

        given(productRepository.findByIdWithLock(1L)).willReturn(Optional.of(product));

        // Act
        ValidationException ex = assertThrows(ValidationException.class,
                () -> paymentValidator.validateInsert(paymentInput));

        //Asserts
        assertEquals("La cantidad no puede ser mayor que el stock del producto", ex.getMessage());

    }

    @Test
    void test02_expectValidationException_whenProductPriceChanged() {

        // Arrange
        Product product = Product.builder()
                .id(1L)
                .stock(5)
                .price(50.0)
                .build();

        PaymentInputDto paymentInput = PaymentInputDto.builder()
                .items(Set.of(OrderItemInputDto.builder()
                        .idProduct(1L)
                        .quantity(3)
                        .unitPrice(0.0)
                        .build()))
                .build();

        given(productRepository.findByIdWithLock(1L)).willReturn(Optional.of(product));

        // Act
        ValidationException ex = assertThrows(ValidationException.class,
                () -> paymentValidator.validateInsert(paymentInput));

        //Asserts
        assertEquals("El precio del producto ha cambiado", ex.getMessage());

    }

    @Test
    void test03_expectValidationException_whenTotalAmountIsZero() {

        // Arrange
        Product product = Product.builder()
                .id(1L)
                .stock(5)
                .price(50.0)
                .build();

        PaymentInputDto paymentInput = PaymentInputDto.builder()
                .totalAmount(0.00)
                .items(Set.of(OrderItemInputDto.builder()
                        .idProduct(1L)
                        .quantity(3)
                        .unitPrice(50.0)
                        .build()))
                .build();

        given(productRepository.findByIdWithLock(1L)).willReturn(Optional.of(product));

        // Act
        ValidationException ex = assertThrows(ValidationException.class,
                () -> paymentValidator.validateInsert(paymentInput));

        //Asserts
        assertEquals("El precio total no puede ser negativo o cero", ex.getMessage());
    }
}
