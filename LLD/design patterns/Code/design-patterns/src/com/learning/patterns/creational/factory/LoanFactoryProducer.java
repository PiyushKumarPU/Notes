package com.learning.patterns.factory;

public class LoanFactoryProducer {
    public static LoanFactory getLoanFactory(LoanType type) {
        return switch (type) {
            case PERSONAL -> new PersonalLoanFactory();
            case CAR -> new CarLoanFactory();
            case HOME -> new HomeLoanFactory();
        };
    }
}