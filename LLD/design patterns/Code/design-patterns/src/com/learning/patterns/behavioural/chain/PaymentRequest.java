package com.learning.patterns.behavioural.chain;

/*
 * Represents a payment request with transaction details.
 */
public class PaymentRequest {
    private double amount;
    private double balance;
    private boolean isKYCVerified;
    private boolean isFraudulent;

    public PaymentRequest(double amount, double balance, boolean isKYCVerified, boolean isFraudulent) {
        this.amount = amount;
        this.balance = balance;
        this.isKYCVerified = isKYCVerified;
        this.isFraudulent = isFraudulent;
    }

    public double getAmount() { return amount; }
    public double getBalance() { return balance; }
    public boolean isKYCVerified() { return isKYCVerified; }
    public boolean isFraudulent() { return isFraudulent; }
}

