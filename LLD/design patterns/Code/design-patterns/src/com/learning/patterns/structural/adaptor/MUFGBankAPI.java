package com.learning.patterns.structural.adaptor;

/*
 * Concrete Adaptee: MUFG Bank
 */
public class MUFGBankAPI implements JapaneseBankAPI {

    @Override
    public String processYenPayment(double yen) {
        return "MUFG Bank processed payment of " + yen + " JPY";
    }
}
