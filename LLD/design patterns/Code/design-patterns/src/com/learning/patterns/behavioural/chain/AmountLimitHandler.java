package com.learning.patterns.behavioural.chain;

/*
 * Validates that transaction amount is within UPI limit (e.g., ₹1,00,000).
 */
public class AmountLimitHandler extends BaseHandler {

    @Override
    public void handle(PaymentRequest request) {
        if (request.getAmount() > 100000) {
            System.out.println("❌ Transaction failed: Amount exceeds UPI limit of ₹1,00,000");
            return;
        }
        System.out.println("✅ Amount check passed");
        passToNext(request);
    }
}

