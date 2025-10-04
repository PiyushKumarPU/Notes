package com.learning.patterns.structural.decorator;

public abstract class OfferDecorator implements PaymentProcessor {
    protected PaymentProcessor wrappedProcessor;

    public OfferDecorator(PaymentProcessor processor) {
        this.wrappedProcessor = processor;
    }

    @Override
    public void processPayment(double amount) {
        wrappedProcessor.processPayment(amount);
    }
}
