package com.programandoenjava.bootcamp_1_2026.payments.model.constants;

public enum StatusPaymentEnum {

    ACCEPTED("ACCEPTED"),
    REQUIRES_VERIFICATION("REQUIRES_VERIFICATION"),
    DETECTED_FRAUD("DETECTED_FRAUD"),
    DECLINED("DECLINED");

    private final String value;

    StatusPaymentEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}