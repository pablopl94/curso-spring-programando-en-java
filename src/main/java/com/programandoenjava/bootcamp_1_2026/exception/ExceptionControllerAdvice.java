package com.programandoenjava.bootcamp_1_2026.exception;

import com.programandoenjava.bootcamp_1_2026.model.ErrorPaymentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<ErrorPaymentResponse> handlePaymentException(PaymentException exception){

        ErrorPaymentResponse error = new ErrorPaymentResponse( exception.getCode(), exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}
