package com.learning.patterns.behavioural.strategy;

/*
 * Strategy Interface
 * - Defines the contract for all payment strategies.
 * - Each payment strategy must implement this interface.
 */
public interface PaymentStrategy {
    String makePayment(double amount);
}
