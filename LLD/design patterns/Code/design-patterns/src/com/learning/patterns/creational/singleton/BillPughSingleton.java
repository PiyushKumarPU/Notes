package com.learning.patterns.singleton;

/*
 * Bill Pugh Singleton Implementation
 *
 * Key points:
 * - Relies on the Java Memory Model’s guarantee that class initialization
 *   is thread-safe.
 * - The inner static helper class (SingletonHelper) is not loaded until
 *   getInstance() is called → "Lazy Initialization".
 * - No synchronization needed, so it's efficient (no performance overhead).
 * - Simple and elegant alternative to Double-Checked Locking.
 */
public class BillPughSingleton {

    // Private constructor prevents instantiation from outside.
    private BillPughSingleton() {
        // Optional: initialization code here
    }

    /*
     * Static inner class - loaded only when referenced.
     * The JVM ensures thread-safety during class initialization.
     */
    private static class SingletonHelper {
        // Final instance created only once when SingletonHelper is loaded.
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    /*
     * Global access point.
     * On the first call, SingletonHelper class is loaded
     * and INSTANCE is initialized.
     * On subsequent calls, the same INSTANCE is returned.
     */
    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}

