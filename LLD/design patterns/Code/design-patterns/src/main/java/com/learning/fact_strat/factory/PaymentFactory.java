package com.learning.fact_strat.factory;

import com.learning.fact_strat.contract.*;
import com.learning.fact_strat.enums.PaymentMode;

public class PaymentFactory {

    public static Payment getPayment(PaymentMode mode) {
        return switch (mode) {
            case CREDIT_CARD -> new CreditCardPayment();
            case DEBIT_CARD -> new DebitCardPayment();
            case UPI -> new UPIPayment();
            case PAYPAL -> new PayPalPayment();
            default -> throw new IllegalArgumentException("Unsupported payment mode: " + mode);
        };
    }
}

