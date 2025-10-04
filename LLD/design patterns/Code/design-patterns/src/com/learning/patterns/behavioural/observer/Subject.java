package com.learning.patterns.behavioural.observer;

/*
 * Subject interface
 * - Declares methods for adding, removing, and notifying observers.
 */
public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String transactionId, String status);
}
