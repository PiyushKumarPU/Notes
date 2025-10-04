package com.learning.patterns.structural.adaptor;

/*
 * Concrete Adaptee: Mizuho Bank
 */
public class MizuhoBankAPI implements JapaneseBankAPI {
    @Override
    public String processYenPayment(double yen) {
        return "Mizuho Bank processed payment of " + yen + " JPY";
    }
}
