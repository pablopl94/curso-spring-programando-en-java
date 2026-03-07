package com.programandoenjava.bootcamp_1_2026.order.exception;

public class OrderServiceException extends RuntimeException {

    private final String code;

    public OrderServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

    public OrderServiceException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}