package com.learning.patterns.behavioural.observer;

/*
 * Observer 3: Notifies the NPCI settlement service for backend reconciliation.
 */
public class NPCISettlementService implements Observer {

    @Override
    public void update(String transactionId, String status) {
        System.out.println("🏦 [NPCI Settlement] Transaction " + transactionId + " sent for settlement with status: " + status);
    }
}
