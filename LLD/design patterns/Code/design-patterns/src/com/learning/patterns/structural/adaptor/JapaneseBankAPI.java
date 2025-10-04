package com.learning.patterns.structural.adaptor;

/*
 * Adaptee Interface
 * - Represents Japanese Bank API with its own method.
 */
public interface JapaneseBankAPI {
    String processYenPayment(double yen);
}
