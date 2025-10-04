package com.learning.patterns.structural.decorator;

public class BasicPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing base payment of ₹" + amount);
    }
}
