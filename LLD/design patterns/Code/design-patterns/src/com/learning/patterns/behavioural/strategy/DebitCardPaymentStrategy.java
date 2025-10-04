package com.learning.patterns.behavioural.strategy;

import java.util.UUID;

/*
 * Concrete Strategy: Debit Card Payment
 * - Implements PaymentStrategy interface
 * - Provides specific logic for debit card payments
 */
public class DebitCardPaymentStrategy implements PaymentStrategy {

    @Override
    public String makePayment(double amount) {
        IO.println("Making debit card payment of amount: " + amount);
        return UUID.randomUUID().toString();
    }
}
