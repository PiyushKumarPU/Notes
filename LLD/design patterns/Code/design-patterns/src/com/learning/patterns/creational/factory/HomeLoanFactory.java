package com.learning.patterns.factory;

public class HomeLoanFactory implements LoanFactory {
    @Override
    public EmiCalculator createEmiCalculator() {
        return new HomeLoanEmiCalculator();
    }

    @Override
    public LoanDocument createLoanDocument() {
        return new HomeLoanDocument();
    }
}