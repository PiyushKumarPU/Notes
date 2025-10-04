package com.learning.patterns.behavioural.chain;

/*
 * Checks if the user has sufficient balance.
 */
public class BalanceHandler extends BaseHandler {

    @Override
    public void handle(PaymentRequest request) {
        if (request.getAmount() > request.getBalance()) {
            System.out.println("❌ Transaction failed: Insufficient balance");
            return;
        }
        System.out.println("✅ Balance check passed");
        passToNext(request);
    }
}

