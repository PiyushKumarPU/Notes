package com.learning.patterns.factory;

public class PersonalLoanEmiCalculator implements EmiCalculator {
    @Override
    public double calculateEmi(double principal, double rate, int tenure) {
        return (principal * rate * tenure) / 100; // simplified logic
    }
}