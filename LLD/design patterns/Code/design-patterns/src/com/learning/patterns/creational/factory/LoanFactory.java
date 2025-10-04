package com.learning.patterns.factory;

public interface LoanFactory {
    EmiCalculator createEmiCalculator();
    LoanDocument createLoanDocument();
}