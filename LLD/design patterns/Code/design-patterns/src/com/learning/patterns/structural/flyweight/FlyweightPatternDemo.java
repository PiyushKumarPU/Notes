package com.learning.patterns.structural.flyweight;

/*
 * Client Class
 * -------------
 * Demonstrates the Flyweight pattern in action for card metadata reuse.
 */
public class FlyweightPatternDemo {

    public static void main(String[] args) {
        System.out.println("===== Flyweight Pattern - FinTech Card Metadata Example =====\n");

        // Shared (Intrinsic) data reused
        CardFlyweight card1 = CardFlyweightFactory.getCardFlyweight("HDFC Bank", "VISA", "INR", "India");
        CardFlyweight card2 = CardFlyweightFactory.getCardFlyweight("HDFC Bank", "VISA", "INR", "India");
        CardFlyweight card3 = CardFlyweightFactory.getCardFlyweight("ICICI Bank", "MasterCard", "INR", "India");

        // Unique (Extrinsic) data per transaction
        CardExtrinsicData txn1 = new CardExtrinsicData("4111111111111111", "12/26", "123", "CUST001");
        CardExtrinsicData txn2 = new CardExtrinsicData("4222222222222222", "10/25", "456", "CUST002");
        CardExtrinsicData txn3 = new CardExtrinsicData("4333333333333333", "11/27", "789", "CUST003");

        // Execute transactions
        card1.displayCardDetails(txn1);
        card2.displayCardDetails(txn2);
        card3.displayCardDetails(txn3);

        System.out.println("Total Flyweight objects created: " + CardFlyweightFactory.getTotalFlyweights());
    }
}

