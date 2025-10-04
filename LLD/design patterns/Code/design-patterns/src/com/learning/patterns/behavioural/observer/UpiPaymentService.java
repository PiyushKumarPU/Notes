package com.learning.patterns.behavioural.observer;


import java.util.ArrayList;
import java.util.List;

/*
 * Concrete Subject
 * - Represents the UPI Payment Service.
 * - Notifies all registered observers when a payment is completed.
 */
public class UpiPaymentService implements Subject {

    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String transactionId, String status) {
        for (Observer observer : observers) {
            observer.update(transactionId, status);
        }
    }

    public void completePayment(String transactionId) {
        System.out.println("💳 Payment successful for Transaction ID: " + transactionId);
        notifyObservers(transactionId, "SUCCESS");
    }
}

