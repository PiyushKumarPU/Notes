package com.learning.patterns.structural.decorator;

public class FestivalOfferDecorator extends OfferDecorator {
    public FestivalOfferDecorator(PaymentProcessor processor) {
        super(processor);
    }

    @Override
    public void processPayment(double amount) {
        double discounted = amount * 0.9; // 10% off
        System.out.println("[Festival Offer] 10% discount applied! New amount: ₹" + discounted);
        super.processPayment(discounted);
    }
}
