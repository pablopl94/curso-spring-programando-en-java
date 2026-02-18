package com.programandoenjava.bootcamp_1_2026.model;

public class PaymentRequest {

    private double amount;

    private String userEmail;

    public static final String supplierEmail = "invoices@codejav-bootcamp.com";

    public double getAmount() {
        return amount;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}
