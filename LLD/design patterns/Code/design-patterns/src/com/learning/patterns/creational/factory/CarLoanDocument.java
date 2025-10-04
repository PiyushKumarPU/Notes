package com.learning.patterns.factory;

public class CarLoanDocument implements LoanDocument {
    @Override
    public String getDocument() { return "Car Loan Agreement"; }
}