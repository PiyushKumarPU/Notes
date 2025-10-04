package com.learning.patterns.behavioural.strategy;

/*
 * Enum to represent supported payment types
 * - Used to decide which PaymentStrategy to use
 */
public enum PaymentType {
    CREDIT,
    DEBIT,
    UPI
}
