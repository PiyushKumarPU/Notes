package com.learning.fact_strat.strategy;

import com.learning.fact_strat.contract.FeeCalculationStrategy;

public class FestiveDiscountStrategy implements FeeCalculationStrategy {

    @Override
    public double apply(double amount) {
        return amount * 0.9; // 10% discount
    }
}
