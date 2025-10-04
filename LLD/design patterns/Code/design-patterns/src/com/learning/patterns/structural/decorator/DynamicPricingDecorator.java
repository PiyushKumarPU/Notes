package com.learning.patterns.structural.decorator;


import java.util.Random;

/*
 * Concrete Decorator: Adds dynamic pricing adjustment based on market conditions.
 * For example: surcharge or discount depending on demand, time, or risk factor.
 */
public class DynamicPricingDecorator extends OfferDecorator {

    public DynamicPricingDecorator(PaymentProcessor processor) {
        super(processor);
    }

    @Override
    public void processPayment(double amount) {
        // Simulate dynamic pricing adjustment: +/- up to 10%
        double adjustmentFactor = getDynamicAdjustment();
        double adjustedAmount = amount * adjustmentFactor;

        if (adjustmentFactor > 1.0) {
            System.out.println("[Dynamic Pricing] Surge applied: +" +
                    Math.round((adjustmentFactor - 1) * 100) + "% | New amount: ₹" + adjustedAmount);
        } else if (adjustmentFactor < 1.0) {
            System.out.println("[Dynamic Pricing] Discount applied: " +
                    Math.round((1 - adjustmentFactor) * 100) + "% | New amount: ₹" + adjustedAmount);
        } else {
            System.out.println("[Dynamic Pricing] No change in price. Amount: ₹" + adjustedAmount);
        }

        super.processPayment(adjustedAmount);
    }

    private double getDynamicAdjustment() {
        // Random value between 0.9 (−10%) and 1.1 (+10%) to simulate market fluctuation
        double min = 0.9, max = 1.1;
        return min + (max - min) * new Random().nextDouble();
    }
}

