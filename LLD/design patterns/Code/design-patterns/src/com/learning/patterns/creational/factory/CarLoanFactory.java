package com.learning.patterns.factory;

public class CarLoanFactory implements LoanFactory {
    @Override
    public EmiCalculator createEmiCalculator() {
        return new CarLoanEmiCalculator();
    }

    @Override
    public LoanDocument createLoanDocument() {
        return new CarLoanDocument();
    }
}