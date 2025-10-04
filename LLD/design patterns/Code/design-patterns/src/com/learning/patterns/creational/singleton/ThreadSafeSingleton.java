package com.learning.patterns.singleton;

/*
 * Thread-Safe Singleton using Double-Checked Locking (DCL)
 *
 * Key points:
 * - Ensures only one instance of the class is created (Singleton property).
 * - Uses 'volatile' to prevent instruction reordering and ensure visibility across threads.
 * - Uses synchronized block with double-check to minimize synchronization overhead.
 */
public class ThreadSafeSingleton {

    // Volatile ensures:
    // 1. Multiple threads handle the 'instance' variable correctly.
    // 2. Prevents "half-constructed" object visibility due to instruction reordering.
    private static volatile ThreadSafeSingleton instance;

    // Private constructor prevents instantiation from outside the class.
    private ThreadSafeSingleton() {
        // Optional: initialization code (resources, configs, etc.)
    }

    /*
     * Global access point to get the Singleton instance.
     *
     * Double-Checked Locking logic:
     * --------------------------------
     * 1. First null check (without lock):
     *    - Fast path for already initialized instance.
     *
     * 2. If still null, synchronize on the class:
     *    - Ensures only one thread initializes the instance.
     *
     * 3. Second null check inside synchronized block:
     *    - Avoids multiple threads creating multiple objects when
     *      they are waiting for the lock.
     *
     * After the first initialization, all subsequent calls are fast (no locking).
     */
    public static ThreadSafeSingleton getInstance() {
        if (instance == null) { // First check (no lock, improves performance)
            synchronized (ThreadSafeSingleton.class) { // Lock only if needed
                if (instance == null) { // Second check (with lock, ensures correctness)
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
}

