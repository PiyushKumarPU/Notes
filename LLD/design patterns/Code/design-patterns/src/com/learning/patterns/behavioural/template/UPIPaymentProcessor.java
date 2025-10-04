package com.learning.patterns.behavioural.template;

/*
 * Concrete Class 1
 * ----------------
 * Implements the steps of payment processing for UPI transactions.
 */
public class UPIPaymentProcessor extends PaymentProcessorTemplate {

    @Override
    protected void validatePayment() {
        System.out.println("[UPI] Validating VPA, limit, and transaction rules...");
    }

    @Override
    protected void authenticateUser() {
        System.out.println("[UPI] Authenticating user using UPI PIN...");
    }

    @Override
    protected void processTransaction() {
        System.out.println("[UPI] Sending payment request to NPCI switch...");
    }

    @Override
    protected void sendNotification() {
        System.out.println("[UPI] Sending transaction confirmation to user app...");
    }
}

