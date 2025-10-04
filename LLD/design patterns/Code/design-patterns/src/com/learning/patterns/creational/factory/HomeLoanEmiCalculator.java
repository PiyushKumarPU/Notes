package com.learning.patterns.factory;

public class HomeLoanEmiCalculator implements EmiCalculator {
    @Override
    public double calculateEmi(double principal, double rate, int tenure) {
        return (principal * rate * tenure) / 240; // simplified
    }
}