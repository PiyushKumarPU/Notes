package com.learning.patterns.behavioural.template;

/*
 * Client Class
 * -------------
 * Demonstrates the Template Method Pattern with UPI and Card payments.
 */
public class TemplatePatternDemo {

    static void main() {

        System.out.println("----- Processing UPI Payment -----");
        PaymentProcessorTemplate upiPayment = new UPIPaymentProcessor();
        upiPayment.processPayment();

        System.out.println("----- Processing Card Payment -----");
        PaymentProcessorTemplate cardPayment = new CardPaymentProcessor();
        cardPayment.processPayment();
    }
}

