package com.learning.patterns.prototype;

/*
 * Prototype Interface
 * - Declares the clone() method that must be implemented by all concrete prototypes.
 * - Ensures that different objects can be copied without depending on their concrete classes.
 */
public interface BasePrototype {
    BasePrototype clone();
}

