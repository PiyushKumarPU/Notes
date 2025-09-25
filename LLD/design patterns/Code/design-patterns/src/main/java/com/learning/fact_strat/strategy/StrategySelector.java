package com.learning.fact_strat.strategy;

import com.learning.fact_strat.contract.FeeCalculationStrategy;

public class StrategySelector {

    public static FeeCalculationStrategy select(String userType, boolean isFestive) {
        // here we can select the strategy based on userType and isFestive
        if (isFestive) {
            return new FestiveDiscountStrategy();
        } else if ("PREMIUM".equalsIgnoreCase(userType)) {
            return new PremiumCustomerStrategy();
        } else {
            return new NoDiscountStrategy();
        }
    }
}

