package com.learning.fact_strat;

import com.learning.fact_strat.contract.Payment;
import com.learning.fact_strat.contract.FeeCalculationStrategy;
import com.learning.fact_strat.enums.PaymentMode;
import com.learning.fact_strat.factory.PaymentFactory;
import com.learning.fact_strat.strategy.StrategySelector;

public class DemoClient {
    public static void main(String[] args) {
        double amount = 1000.0;

        // Case 1: Credit Card + Festive Discount
        Payment payment1 = PaymentFactory.getPayment(PaymentMode.CREDIT_CARD);
        FeeCalculationStrategy strategy1 = StrategySelector.select("REGULAR", true);
        payment1.setStrategy(strategy1);
        System.out.println(payment1.pay(amount));

        // Case 2: UPI + Premium Customer
        Payment payment2 = PaymentFactory.getPayment(PaymentMode.UPI);
        FeeCalculationStrategy strategy2 = StrategySelector.select("PREMIUM", false);
        payment2.setStrategy(strategy2);
        System.out.println(payment2.pay(amount));

        // Case 3: PayPal + No Discount
        Payment payment3 = PaymentFactory.getPayment(PaymentMode.PAYPAL);
        FeeCalculationStrategy strategy3 = StrategySelector.select("REGULAR", false);
        payment3.setStrategy(strategy3);
        System.out.println(payment3.pay(amount));
    }
}
