package com.learning.patterns.structural.adaptor;

/*
 * Concrete Adaptee: SMBC Bank
 */
public class SMBCBankAPI implements JapaneseBankAPI {
    @Override
    public String processYenPayment(double yen) {
        return "SMBC Bank processed payment of " + yen + " JPY";
    }
}
