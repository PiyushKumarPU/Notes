package com.learning.patterns.behavioural.chain;

/*
 * Checks if the user has completed KYC.
 */
public class KYCHandler extends BaseHandler {

    @Override
    public void handle(PaymentRequest request) {
        if (!request.isKYCVerified()) {
            System.out.println("❌ Transaction failed: KYC not completed");
            return;
        }
        System.out.println("✅ KYC verification passed");
        passToNext(request);
    }
}

