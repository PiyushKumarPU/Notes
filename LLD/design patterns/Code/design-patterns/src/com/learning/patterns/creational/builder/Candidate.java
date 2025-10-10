package com.learning.patterns.creational.builder;

import lombok.Getter;

/*
 * Candidate class
 * Represents a job applicant with personal, professional, and skill details.
 */
@Getter
public class Candidate {

    // --- Getters (you can keep or remove setters for immutability) ---
    // --- Personal Information ---
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String email;
    private String phone;

    // --- Private Constructor (only accessible via Builder) ---
    private Candidate() {
    }

    // --- Builder Class ---
    public static class CandidateBuilder {
        // same fields as Candidate
        private String firstName;
        private String lastName;
        private int age;
        private String gender;
        private String email;
        private String phone;

        private CandidateBuilder() {
        }

        public static CandidateBuilder builder() {
            return new CandidateBuilder();
        }

        // --- Fluent Builder methods ---
        public CandidateBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public CandidateBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public CandidateBuilder age(int age) {
            this.age = age;
            return this;
        }

        public CandidateBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public CandidateBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CandidateBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        // --- Build method ---
        public Candidate build() {
            Candidate candidate = new Candidate();
            candidate.firstName = this.firstName;
            candidate.lastName = this.lastName;
            candidate.age = this.age;
            candidate.gender = this.gender;
            candidate.email = this.email;
            candidate.phone = this.phone;

            return candidate;
        }
    }
}
