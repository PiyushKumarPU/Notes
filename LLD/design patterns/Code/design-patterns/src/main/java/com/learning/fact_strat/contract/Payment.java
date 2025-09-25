package com.learning.fact_strat.contract;

public interface Payment {
    void setStrategy(FeeCalculationStrategy strategy);
    String pay(double amount);
}
