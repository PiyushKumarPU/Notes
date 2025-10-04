package com.learning.patterns.factory;

public class PersonalLoanDocument implements LoanDocument {
    @Override
    public String getDocument() { return "Personal Loan Agreement"; }
}