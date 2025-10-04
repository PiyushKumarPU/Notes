package com.learning.patterns.behavioural.strategy;

/*
 * Factory class to return appropriate PaymentStrategy
 * based on the selected PaymentType
 */
public class PaymentStrategyFactory {

    public static PaymentStrategy getPaymentStrategy(PaymentType paymentType) {
        return switch (paymentType) {
            case CREDIT -> new CreditCardPaymentStrategy();
            case DEBIT -> new DebitCardPaymentStrategy();
            case UPI -> new UPIPaymentStrategy();
        };
    }
}
