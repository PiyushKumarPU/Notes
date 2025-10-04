package com.learning.patterns.creational.builder;

/*
 * RecordBuilder using Java Records + Builder Pattern
 *
 * - Java records (introduced in Java 16) are immutable data carriers by default.
 * - They automatically generate constructor, getters, equals, hashCode, and toString.
 * - However, records don’t directly support the Builder pattern.
 * - This example shows how to combine a record with a nested Builder class
 *   for convenient and fluent object construction.
 */
public record RecordBuilder(String name, int age, String email) {

    /*
     * Static nested Builder class for RecordBuilder
     * - Provides a fluent API for building record instances
     * - Useful when the record has many fields (avoids long constructors)
     */
    public static class Builder {
        private String name;
        private int age;
        private String email;

        // Fluent setter for 'name'
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        // Fluent setter for 'age'
        public Builder age(int age) {
            this.age = age;
            return this;
        }

        // Fluent setter for 'email'
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        /*
         * Final build method
         * - Returns a new immutable RecordBuilder instance
         * - Internally calls the canonical constructor of the record
         */
        public RecordBuilder build() {
            return new RecordBuilder(name, age, email);
        }
    }
}


