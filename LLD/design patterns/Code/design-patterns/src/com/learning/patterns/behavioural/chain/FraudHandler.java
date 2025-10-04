package com.learning.patterns.behavioural.chain;

/*
 * Checks if the transaction is marked as fraudulent.
 */
public class FraudHandler extends BaseHandler {

    @Override
    public void handle(PaymentRequest request) {
        if (request.isFraudulent()) {
            System.out.println("❌ Transaction blocked: Fraud detected");
            return;
        }
        System.out.println("✅ Fraud check passed");
        passToNext(request);
    }
}

