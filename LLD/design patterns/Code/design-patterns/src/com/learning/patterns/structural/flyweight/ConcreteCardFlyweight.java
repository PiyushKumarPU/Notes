package com.learning.patterns.structural.flyweight;

/*
 * Concrete Flyweight Class
 * -------------------------
 * Stores intrinsic (shared) data such as bank name, card type, currency, etc.
 * These objects are reused for multiple transactions to save memory.
 */
public class ConcreteCardFlyweight implements CardFlyweight {

    private final String bankName;
    private final String cardType;
    private final String currency;
    private final String issuerCountry;

    public ConcreteCardFlyweight(String bankName, String cardType, String currency, String issuerCountry) {
        this.bankName = bankName;
        this.cardType = cardType;
        this.currency = currency;
        this.issuerCountry = issuerCountry;
    }

    @Override
    public void displayCardDetails(CardExtrinsicData extrinsicData) {
        System.out.println("----- Card Transaction Details -----");
        System.out.println("Bank Name     : " + bankName);
        System.out.println("Card Type     : " + cardType);
        System.out.println("Currency      : " + currency);
        System.out.println("Issuer Country: " + issuerCountry);
        System.out.println("Card Number   : " + extrinsicData.cardNumber());
        System.out.println("Expiry Date   : " + extrinsicData.expiryDate());
        System.out.println("Customer ID   : " + extrinsicData.customerId());
        System.out.println("------------------------------------\n");
    }
}
