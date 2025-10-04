package com.learning.patterns.structural.decorator;

public enum OfferType {
    FESTIVAL(1),
    DYNAMIC_PRICING(2),
    CASHBACK(3);

    private final int priority;
    OfferType(int priority) { this.priority = priority; }
    public int getPriority() { return priority; }
}
