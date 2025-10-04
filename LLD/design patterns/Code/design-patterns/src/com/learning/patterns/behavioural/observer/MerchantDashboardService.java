package com.learning.patterns.behavioural.observer;

/*
 * Observer 2: Updates merchant dashboard in real-time.
 */
public class MerchantDashboardService implements Observer {

    @Override
    public void update(String transactionId, String status) {
        System.out.println("💼 [Merchant Dashboard] Transaction " + transactionId + " updated with status: " + status);
    }
}
