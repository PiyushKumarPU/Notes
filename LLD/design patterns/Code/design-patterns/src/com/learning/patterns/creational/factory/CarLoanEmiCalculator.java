package com.learning.patterns.factory;

public class CarLoanEmiCalculator implements EmiCalculator {
    @Override
    public double calculateEmi(double principal, double rate, int tenure) {
        return (principal * rate * tenure) / 120; // simplified
    }
}