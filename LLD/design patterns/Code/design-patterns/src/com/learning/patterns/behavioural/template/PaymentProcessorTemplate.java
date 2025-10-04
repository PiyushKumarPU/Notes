package com.learning.patterns.behavioural.template;

/*
 * Abstract Template Class
 * ------------------------
 * Defines the "template method" — a fixed workflow for processing a payment.
 * Subclasses can provide custom implementations for each step but
 * cannot change the order of execution.
 */
public abstract class PaymentProcessorTemplate {

    // Template method - defines the algorithm structure
    public final void processPayment() {
        validatePayment();
        authenticateUser();
        processTransaction();
        sendNotification();
        System.out.println("✅ Payment flow completed.\n");
    }

    // Abstract steps to be implemented by subclasses
    protected abstract void validatePayment();
    protected abstract void authenticateUser();
    protected abstract void processTransaction();
    protected abstract void sendNotification();
}

