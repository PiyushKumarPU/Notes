package com.learning.fact_strat.contract;

public class CreditCardPayment implements Payment {
    private FeeCalculationStrategy strategy;

    @Override
    public void setStrategy(FeeCalculationStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public String pay(double amount) {
        double finalAmount = strategy.apply(amount);
        return "Paid " + finalAmount + " using Credit Card";
    }
}

