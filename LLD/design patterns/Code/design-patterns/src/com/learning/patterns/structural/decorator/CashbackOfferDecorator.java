package com.learning.patterns.structural.decorator;

public class CashbackOfferDecorator extends OfferDecorator {
    public CashbackOfferDecorator(PaymentProcessor processor) {
        super(processor);
    }

    @Override
    public void processPayment(double amount) {
        super.processPayment(amount);
        double cashback = amount * 0.05;
        System.out.println("[Cashback Offer] ₹" + cashback + " cashback will be credited!");
    }
}
