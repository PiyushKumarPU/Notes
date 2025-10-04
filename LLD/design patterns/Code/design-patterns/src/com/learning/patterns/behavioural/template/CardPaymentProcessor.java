package com.learning.patterns.behavioural.template;

/*
 * Concrete Class 2
 * ----------------
 * Implements the steps of payment processing for Credit/Debit Card transactions.
 */
public class CardPaymentProcessor extends PaymentProcessorTemplate {

    @Override
    protected void validatePayment() {
        System.out.println("[Card] Validating card number, expiry date, and CVV...");
    }

    @Override
    protected void authenticateUser() {
        System.out.println("[Card] Authenticating user using OTP via issuer bank...");
    }

    @Override
    protected void processTransaction() {
        System.out.println("[Card] Routing transaction through Visa/MasterCard network...");
    }

    @Override
    protected void sendNotification() {
        System.out.println("[Card] Sending SMS/email confirmation to cardholder...");
    }
}

