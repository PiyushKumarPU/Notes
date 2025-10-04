package com.learning.patterns.factory;

public class HomeLoanDocument implements LoanDocument {
    @Override
    public String getDocument() { return "Home Loan Agreement"; }
}