package com.programandoenjava.bootcamp_1_2026.model;

public class ErrorPaymentResponse {

    private String code;
    private String message;

    public ErrorPaymentResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
