package com.learning.patterns.behavioural.strategy;

/*
 * Client code demonstrating Strategy Pattern usage
 */
public class StrategyPatternDemo {
    public static void main(String[] args) {
        // Choose strategy dynamically at runtime
        PaymentStrategy strategy = PaymentStrategyFactory.getPaymentStrategy(PaymentType.UPI);

        // Execute payment
        String txnId = strategy.makePayment(5000);
        System.out.println("Transaction successful with ID: " + txnId);
    }
}
