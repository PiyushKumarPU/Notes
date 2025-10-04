package com.learning.patterns.structural.adaptor;

/*
 * Adapter class
 * - Adapts the JapaneseBankAPI to the PaymentGateway expected by PayPay.
 */
public class PayPayPaymentAdapter implements PaymentGateway {

    private JapaneseBankAPI bankApi;

    public PayPayPaymentAdapter(JapaneseBankAPI bankApi) {
        this.bankApi = bankApi;
    }

    @Override
    public String pay(double amount) {
        // Delegate to the adaptee method
        return bankApi.processYenPayment(amount);
    }
}
