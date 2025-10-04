package com.learning.patterns.behavioural.chain;

/*
 * BaseHandler:
 * Provides default implementation for setting and calling the next handler.
 */
public abstract class BaseHandler implements PaymentHandler {

    protected PaymentHandler nextHandler;

    @Override
    public void setNext(PaymentHandler next) {
        this.nextHandler = next;
    }

    protected void passToNext(PaymentRequest request) {
        if (nextHandler != null) {
            nextHandler.handle(request);
        } else {
            System.out.println("✅ Transaction passed all validations successfully!");
        }
    }
}

