package com.learning.patterns.factory;

public class PersonalLoanFactory implements LoanFactory {
    @Override
    public EmiCalculator createEmiCalculator() {
        return new PersonalLoanEmiCalculator();
    }

    @Override
    public LoanDocument createLoanDocument() {
        return new PersonalLoanDocument();
    }
}