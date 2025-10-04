package com.learning.patterns.behavioural.strategy;

import java.util.UUID;

/*
 * Concrete Strategy: Credit Card Payment
 * - Implements PaymentStrategy interface
 * - Provides specific logic for credit card payments
 */
public class CreditCardPaymentStrategy implements PaymentStrategy {

    @Override
    public String makePayment(double amount) {
        IO.println("Making credit card payment of amount: " + amount);
        return UUID.randomUUID().toString(); // Simulated transaction ID
    }
}
