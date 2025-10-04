package com.learning.patterns.factory;

public class AbstractFactoryDemo {
    public static void main(String[] args) {
        // Get factory for Personal Loan
        LoanFactory personalLoanFactory = LoanFactoryProducer.getLoanFactory(LoanType.PERSONAL);

        EmiCalculator calculator = personalLoanFactory.createEmiCalculator();
        LoanDocument document = personalLoanFactory.createLoanDocument();

        System.out.println("EMI: " + calculator.calculateEmi(100000, 10, 12));
        System.out.println("Document: " + document.getDocument());
    }
}