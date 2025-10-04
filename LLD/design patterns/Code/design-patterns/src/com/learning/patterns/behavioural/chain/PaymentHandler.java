package com.learning.patterns.behavioural.chain;

/*
 * Handler Interface:
 * Defines the structure for setting the next handler and handling the request.
 */
public interface PaymentHandler {

    void setNext(PaymentHandler next);

    void handle(PaymentRequest request);
}

