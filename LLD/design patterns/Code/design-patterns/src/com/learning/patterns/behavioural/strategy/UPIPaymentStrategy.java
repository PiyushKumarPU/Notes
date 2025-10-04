package com.learning.patterns.behavioural.strategy;

import java.util.UUID;

/*
 * Concrete Strategy: UPI Payment
 * - Implements PaymentStrategy interface
 * - Provides specific logic for UPI payments
 */
public class UPIPaymentStrategy implements PaymentStrategy {

    @Override
    public String makePayment(double amount) {
        IO.println("Making UPI payment of amount: " + amount);
        return UUID.randomUUID().toString();
    }
}
