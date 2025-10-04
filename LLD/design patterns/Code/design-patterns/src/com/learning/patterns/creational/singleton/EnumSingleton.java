package com.learning.patterns.singleton;

/*
 * Enum Singleton (Best Practice in Java)
 *
 * - The simplest and most effective way to implement Singleton in Java.
 * - Enum guarantees:
 *     1. Thread-safety by default.
 *     2. Only one instance is ever created (JVM ensures this).
 *     3. Protection against reflection attacks.
 *     4. Protection against serialization (prevents creating new instances during deserialization).
 *
 * - Usage: EnumSingleton.INSTANCE
 */
public enum EnumSingleton {

    // The single instance of this Singleton
    INSTANCE;

    /*
     * Example method inside Singleton
     * (You can add your own business logic here).
     */
    public void doSomething() {
        System.out.println("Singleton method executed!");
    }

    /*
     * (Optional) Getter for instance.
     * Not really needed since Enum provides INSTANCE directly.
     */
    public EnumSingleton getInstance() {
        return INSTANCE;
    }
}

