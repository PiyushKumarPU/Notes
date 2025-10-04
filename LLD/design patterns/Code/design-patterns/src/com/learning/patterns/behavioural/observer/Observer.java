package com.learning.patterns.behavioural.observer;

/*
 * Observer interface
 * - Defines the contract for all observers that want to be notified
 *   when a UPI transaction's status changes.
 */
public interface Observer {
    void update(String transactionId, String status);
}

