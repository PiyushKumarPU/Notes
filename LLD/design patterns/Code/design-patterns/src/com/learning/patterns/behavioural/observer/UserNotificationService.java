package com.learning.patterns.behavioural.observer;


/*
 * Observer 1: Sends payment confirmation to user via SMS or app.
 */
public class UserNotificationService implements Observer {

    @Override
    public void update(String transactionId, String status) {
        System.out.println("📱 [User Notification] Transaction " + transactionId + " status: " + status);
    }
}

