package com.learning.fact_strat.strategy;

import com.learning.fact_strat.contract.FeeCalculationStrategy;

public class NoDiscountStrategy implements FeeCalculationStrategy {
    @Override
    public double apply(double amount) {
        return amount; // no discount
    }
}

