package com.learning.patterns.behavioural.observer;

/*
 * Client: Demonstrates the Observer Pattern in action for UPI payment notifications.
 */
public class ObserverPatternDemo {
    public static void main(String[] args) {

        // Create the UPI Payment Service (Subject)
        UpiPaymentService upiService = new UpiPaymentService();

        // Register observers
        upiService.registerObserver(new UserNotificationService());
        upiService.registerObserver(new MerchantDashboardService());
        upiService.registerObserver(new NPCISettlementService());

        // Complete a payment
        upiService.completePayment("TXN123456789");
    }
}

