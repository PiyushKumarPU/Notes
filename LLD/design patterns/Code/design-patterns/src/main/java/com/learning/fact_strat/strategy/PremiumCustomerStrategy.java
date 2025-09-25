package com.learning.fact_strat.strategy;

import com.learning.fact_strat.contract.FeeCalculationStrategy;

public class PremiumCustomerStrategy implements FeeCalculationStrategy {
    @Override
    public double apply(double amount) {
        return amount * 0.95; // 5% discount
    }
}

