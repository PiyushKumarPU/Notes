package com.learning.patterns.structural.adaptor;

/*
 * Target interface
 * - Defines the payment method expected by PayPay system.
 */
public interface PaymentGateway {
    String pay(double amount);
}
