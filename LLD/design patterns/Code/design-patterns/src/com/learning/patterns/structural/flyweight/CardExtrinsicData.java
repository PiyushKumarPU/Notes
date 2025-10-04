package com.learning.patterns.structural.flyweight;

/*
 * Extrinsic Data Class
 * --------------------
 * Holds data that is unique per transaction or card instance.
 * These details are not shared.
 */
public record CardExtrinsicData(String cardNumber, String expiryDate, String cvv, String customerId) {

}
