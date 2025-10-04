package com.learning.patterns.singleton;

/*
 * Basic Singleton with no thread safety
 *
 * A Singleton ensures that only one instance of a class exists
 * throughout the application and provides a global access point to it.
 *
 * This implementation is the simplest form of Singleton in Java.
 * However, it is **NOT THREAD-SAFE**, meaning multiple threads calling
 * getInstance() at the same time may create multiple instances.
 */
public class BasicSingleton {

    // Static reference to the single instance of this class.
    // Initially set to null until first accessed.
    private static BasicSingleton instance;

    /*
     * Private constructor prevents instantiation from outside the class.
     * Only this class controls instance creation.
     */
    private BasicSingleton() {
    }

    /*
     * Provides a global access point to get the single instance.
     *
     * - On the first call, if 'instance' is null,
     *   a new BasicSingleton object is created.
     *
     * - On subsequent calls, the same instance is returned.
     *
     * ⚠ Limitation: Not thread-safe.
     *   If two threads enter this method at the same time
     *   when 'instance' is still null, both may create new objects,
     *   breaking the Singleton guarantee.
     */
    public static BasicSingleton getInstance() {
        if (instance == null) {
            instance = new BasicSingleton();
        }
        return instance;
    }
}

