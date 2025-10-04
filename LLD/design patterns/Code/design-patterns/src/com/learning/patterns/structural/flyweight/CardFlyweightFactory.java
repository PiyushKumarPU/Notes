package com.learning.patterns.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/*
 * Flyweight Factory
 * -----------------
 * Manages creation and reuse of Flyweight objects.
 * Ensures that identical intrinsic data (bank, card type, etc.) share the same object.
 */
public class CardFlyweightFactory {

    private static final Map<String, CardFlyweight> flyweights = new HashMap<>();

    public static CardFlyweight getCardFlyweight(String bankName, String cardType, String currency, String issuerCountry) {
        String key = bankName + "-" + cardType + "-" + currency + "-" + issuerCountry;
        CardFlyweight flyweight = flyweights.get(key);

        if (flyweight == null) {
            flyweight = new ConcreteCardFlyweight(bankName, cardType, currency, issuerCountry);
            flyweights.put(key, flyweight);
            System.out.println("🆕 Creating new Flyweight for key: " + key);
        } else {
            System.out.println("♻️ Reusing existing Flyweight for key: " + key);
        }

        return flyweight;
    }

    public static int getTotalFlyweights() {
        return flyweights.size();
    }
}

