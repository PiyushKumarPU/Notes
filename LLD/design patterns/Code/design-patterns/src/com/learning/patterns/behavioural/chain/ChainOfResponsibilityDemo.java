package com.learning.patterns.behavioural.chain;


/*
 * Client: Builds the chain and processes a payment request.
 */
public class ChainOfResponsibilityDemo {
    static void main() {

        // Build the chain of handlers
        PaymentHandler amountHandler = new AmountLimitHandler();
        PaymentHandler balanceHandler = new BalanceHandler();
        PaymentHandler kycHandler = new KYCHandler();
        PaymentHandler fraudHandler = new FraudHandler();

        // chaining handler one after other
        amountHandler.setNext(balanceHandler);
        balanceHandler.setNext(kycHandler);
        kycHandler.setNext(fraudHandler);

        // Create a payment request
        PaymentRequest request = new PaymentRequest(
                25000,       // amount
                20000,      // balance
                true,       // KYC verified
                false       // not fraudulent
        );

        // Start the chain
        amountHandler.handle(request);
    }
}

