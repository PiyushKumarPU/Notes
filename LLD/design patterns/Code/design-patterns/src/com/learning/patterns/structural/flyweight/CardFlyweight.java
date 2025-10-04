package com.learning.patterns.structural.flyweight;

/*
 * Flyweight Interface
 * -------------------
 * Declares the operation that Flyweight objects must implement.
 * The method takes "extrinsic" data from outside (like card number, expiry, etc.).
 */
public interface CardFlyweight {
    void displayCardDetails(CardExtrinsicData extrinsicData);
}
